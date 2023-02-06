#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/socket.h>
#include <sys/ioctl.h>
#include <arpa/inet.h>
#include <netinet/in.h>

#include "socket.h"

static int stop_execution = 0;
static void signal_handler(int sig) {
	sig = sig;
	stop_execution = 1;
	fclose(stdin);
	printf("\n");
}

void signal_handler(int sig);
int create_children(int *fd1, int *fd2);
int menu(int sock, int *fd1, int *fd2);
int get_job(int sock, int *fd1, int *fd2);
int get_msg(int sock, int *fd);

int main(int argc, char *argv[])
{
	if (argc != 3) {
		fprintf(stderr, "Usage: %s <IP> <port>\n", argv[0]);
		exit(EXIT_FAILURE);
	}
	
	struct sigaction sigact;
	memset(&sigact, 0, sizeof(struct sigaction));
	sigact.sa_handler = signal_handler;
	sigact.sa_flags = SA_RESTART;
	sigaction(SIGINT, &sigact, NULL);
	
	char *ip = argv[1];
	char *port = argv[2];
	
	// start internet tcp connection
	int sock = create_socket(1, ip, port);
	if (sock == -1) {
		fprintf(stderr, "Error! Failed to create socket.\n");
		exit(EXIT_FAILURE);
	}
	
	/* fd = file descriptor */
	int fd1[2]; // stdout child
	int fd2[2]; // stderr child

	/* Fork process and create children. Based on return value we know where in
	 * the process the program failed, and can clean up accordingly. If nothing
	 * went wrong we'll just carry on.
	*/
	switch(create_children(fd1, fd2)) {
		case 1:
			exit(EXIT_FAILURE);
		case 2:
			goto EXIT_FD1;
		case 3:
			goto EXIT_FD2;
		case 4:
			goto EXIT_CHILD1;
	}
	
	// start menu
	while ((menu(sock, fd1, fd2) == 1) && !stop_execution) {}

	
	// initiate shutdown sequence
	printf("shuting down child 2\n");
	dprintf(fd2[1], "%c", 0);
	
	
	EXIT_CHILD1: // for when creating child 2 fail
	printf("shuting down child 1\n");
	dprintf(fd1[1], "%c", 0);
	sleep(1);
	
	EXIT_FD2: // for when creating child 1 fail
	close(fd2[1]);
	
	EXIT_FD1: // for when creating fd2 fail
	close(fd1[1]);
	return 0;
}

/* Create pipes and forks
 *
 * Return:
 * 0: no error
 * 1: pipe(fd1) failed
 * 2: pipe(fd2) failed
 * 3: fork() child1 failed
 * 4: fork() child2 failed
 */
int create_children(int *fd1, int *fd2) {
	/*
	 * fd[0] = read
	 * fd[1] = write
	 */
	if (pipe(fd1) == -1) {
		perror("pipe()");
		return 1;
	}
	if (pipe(fd2) == -1) {
		perror("pipe()");
		return 2;
	}

	pid_t pid1 = fork();
	if (pid1 == -1) {
		perror("fork()");
		return 3;
	}
	
	// variables for the children
	char *buff = NULL;
	int i = 0;
	unsigned char byte = 0;
	
	// child 1 - stdout
	if (pid1 == 0) {
		// close write side. don't need it.
		close(fd2[1]);
		close(fd2[0]);
		close(fd2[1]);
		
		for (;;) { // while true loop
			i = read(fd1[0], &byte, 1);
			if (i != 1 || byte == 0) { // exit signal
				printf("exiting child 1\n");
				close(fd1[0]);
				_exit(EXIT_SUCCESS);
			}
			buff = malloc(byte+1);
			buff[0] = byte;
			memset (buff, 0, byte+1); // initialize array
			if (read(fd1[0], buff, byte) != byte) {
				perror("read(fd1)");
				fprintf(stderr, "error: received \"%s\"\n", buff);
				close(fd1[0]);
			}
			fprintf(stdout, "%s\n", buff);
			free(buff);
		}
	}
	
	pid_t pid2 = fork();
	if (pid2 == -1) {
		perror("fork()");
		return 4;
	}
	
	// child 2 - stderr
	if (pid2 == 0) {
		// close write side. don't need it.
		close(fd2[1]);
		close(fd1[0]);
		close(fd1[1]);
		
		for (;;) { // while true loop 
			i = read(fd2[0], &byte, 1);
			if (i != 1 || byte == 0) { // exit signal
				printf("exiting child 2\n");
				close(fd2[0]);
				_exit(EXIT_SUCCESS);
			}
			buff = malloc(byte+1);
			buff[0] = byte;
			memset (buff, 0, byte+1); // initialize array
			if (read(fd2[0], buff, byte) != byte) {
				perror("read(fd2)");
				fprintf(stderr, "error: received \"%s\"\n", buff);
				close(fd2[0]);
			}
			fprintf(stderr, "%s\n", buff);
			free(buff);
		}
	}
	
	/* parent */
	close(fd1[0]);
	close(fd2[0]);
	
	return 0;
}

/* Display the menu and catches user input
 *
 * Return:
 * 1: continue running menu loop
 * 0: quit
 */
int menu(int sock, int *fd1, int *fd2) {
	int input = -1; // user input
	char quit[1] = {'T'};
	sleep(1); // make sure children are done printing
	printf("Enter a number\n");
	printf("1 - Fetch job from server\n");
	printf("2 - Fetch a specific number of jobs\n");
	printf("3 - Fetch all the jobs from the server\n");
	printf("4 - Quit\n");
	printf("> ");
	if (scanf("%d", &input) != 1) {
		if (input == -1) {
			if (send(sock, quit, 1, 0) == -1) {
				perror("send()");
			}
			stop_execution = 1;
			return 1; // for sigint
		}
		while (getchar() != '\n') {
			input = -1;
		}
	}
	switch(input) {
		case 1:
			if (get_job(sock, fd1, fd2)) {
				return 0;
			}
			break;
		case 2:
			input = -1;
			printf("Enter number of jobs to fetch\n> ");
			if (scanf("%d", &input) == 1) {
				while (input-- > 0) {
					if (get_job(sock, fd1, fd2)) {
						return 0;
					}
				}
			}
			else {
				if (input == -1) {
					if (send(sock, quit, 1, 0) == -1) {
						perror("send()");
					}
					stop_execution = 1;
					return 1; // for sigint
				}
				while (getchar() != '\n') {}
				fprintf(stderr, "%sInvalid input%s\n", "\x1B[31m", "\x1B[0m");
			}
			break;
		case 3:
			while (!get_job(sock, fd1, fd2)) {}
			return 0;
			break;
		case 4:
			if (send(sock, quit, 1, 0) == -1) {
				perror("send()");
			}
			return 0;
		default:
			fprintf(stderr, "%sInvalid input%s\n", "\x1B[31m", "\x1B[0m");
	}
	return 1;
}

/* Get job type from server and execute based on char value
 *
 * Return:
 * 0: all good
 * 1: signal quit
 */
int get_job(int sock, int *fd1, int *fd2) {
	char byte[1];
	byte[0] = 'G';
	if (send(sock, byte, 1, 0) == -1) {
		perror("send()");
		return 1;
	}
	if (recv(sock, byte, 1, 0) == -1) {
		perror("recv()");
		return 1;
	}
	switch (byte[0]) {
		case 'O':
			return get_msg(sock, fd1);
		case 'E':
			return get_msg(sock, fd2);
		case 'Q':
			fprintf(stderr, "%sRecieved 'kill children' signal.%s\n","\x1B[31m","\x1B[0m");
			byte[0] = 'T';
			if (send(sock, byte, 1, 0) == -1) {
				perror("send()");
			}
			return 1;
		default:
			fprintf(stderr, "%sInvalid job command from server.%s\n", "\x1B[31m", "\x1B[0m");
			byte[0] = 'E';
			if (send(sock, byte, 1, 0) == -1) {
				perror("send()");
			}
			return 1;
	}
}


/* Get message length and text
 *
 * Return:
 * 0: all good
 * 1: error
 */
int get_msg(int sock, int *fd) {
	char byte[1];
	char answer[1] = {'G'};
	
	// tell server it got job type and want to continue
	if (send(sock, answer, 1, 0) == -1) {
		perror("send()");
		return 1;
	}
	
	
	// get msg length
	if (recv(sock, byte, 1, 0) == -1) {
		perror("recv()");
		return 1;
	}
	if (!byte[0]) { // if byte[0] is 0
		fprintf(stderr, "%sReached EOF.%s\n", "\x1B[31m", "\x1B[0m");
		answer[0] = 'E'; // terminate
		send(sock, answer, 1, 0);
		return 1;
	}
	if (send(sock, answer, 1, 0) == -1) {
		perror("send()");
		return 1;
	}
	
	unsigned char len = byte[0];
	char *buff = malloc(len+2);
	buff[0] = len;
	
	// recv msg
	for (int i=1; i<len+1; i++) {
		if (recv(sock, byte, 1, 0) == -1) {
			perror("recv()");
			free(buff);
			return 1;
		}
		if (!byte[0]) { // if byte[0] is 0
			fprintf(stderr, "%sReached EOF.%s\n", "\x1B[31m", "\x1B[0m");
			answer[0] = 'E'; // terminate
			send(sock, answer, 1, 0);
			free(buff);
			return 1;
		}
		if (send(sock, answer, 1, 0) == -1) {
			perror("send()");
			free(buff);
			return 1;
		}
		buff[i] = byte[0];
	}
	buff[len+1] = '\0';
	printf("sending to child %s\n", buff);
	if (dprintf(fd[1], "%s", buff) == -1) {
		perror("dprintf()");
		free(buff);
		return 1;
	}
	free(buff);
	return 0;
}
	
	
