#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#include "socket.h"

int sock;
int client_sock;
FILE *fp;

static int stop_execution = 0;
static void signal_handler(int sig) {
	sig = sig;
	close(sock);
	close(client_sock);
	fclose(fp);
	printf("\n");
	exit(EXIT_SUCCESS);
}

int packet_handling(int sock, int *error);
int talk_to_client(int sock, FILE *fp, int *read_eof, int *error);

int main(int argc, char *argv[])
{
	int read_eof = 0;
	int error = 0;
	short port_swp;
	struct sigaction sigact;
	memset(&sigact, 0, sizeof(struct sigaction));
	sigact.sa_handler = signal_handler;
	sigact.sa_flags = SA_RESTART;
	sigaction(SIGINT, &sigact, NULL);

	if (argc != 3) {
		fprintf(stderr, "Usage: %s <file.job> <port>\n", argv[0]);
		exit(EXIT_FAILURE);
	}
	
	// check if file exists and is readable
	if (access(argv[1], R_OK) == -1) { 
		perror("access()");
		exit(EXIT_FAILURE);
	}
	
	fp = fopen(argv[1], "r");
	if (fp == NULL) {
		fprintf(stderr, "%sError! Failed to open file.%s\n", "\x1B[31m", "\x1B[0m");
		exit(EXIT_FAILURE);
	}
	
	sock = create_socket(0, NULL, argv[2]);
	if (sock == -1) {
		fprintf(stderr, "%sError! Failed to create socket.%s\n", "\x1B[31m", "\x1B[0m");
		fclose(fp);
		exit(EXIT_FAILURE);
	}
	
	while (!stop_execution) {
		client_sock = accept(sock, NULL, NULL);
		port_swp = sock;
		close(sock);
		while (talk_to_client(client_sock, fp, &read_eof, &error)) {}
		if (read_eof) {
			//break; // stop server on EOF
		}
		if (error) {
			break;
		}
		close(client_sock);
		sock = port_swp;
		sock = create_socket(0, NULL, argv[2]);
		if (sock == -1) {
			fprintf(stderr, "%sError! Failed to create socket.%s\n", "\x1B[31m", "\x1B[0m");
			fclose(fp);
			exit(EXIT_FAILURE);
		}
	}
	
	fclose(fp);
	close(client_sock);
	close(sock);

	return 0;
}

/* Interpret request/response from client
 * int *error: error flag
 * 
 * Return: 0 to continue, 1 quit
 */
int packet_handling(int sock, int *error) {
	char byte[1];
	if (recv(sock, byte, 1, 0) == -1) {
		perror("recv()");
		exit(EXIT_FAILURE);
	}
	switch (byte[0]) {
		case 'G':
			return 0;
		case 'T':
			fprintf(stderr, "%sClient terminated.%s\n", "\x1B[31m", "\x1B[0m");
			return 1;
		case 'E':
			fprintf(stderr, "%sClient terminated because of error.%s\n", "\x1B[31m", "\x1B[0m");
			*error = 1;
			return 1;
		default:
			fprintf(stderr, "%sClient sent undefined byte: (int)%i%s\n", "\x1B[31m", byte[0], "\x1B[0m");
			*error = 1;
			return 1;
	}
}

/* Send job type, msg length, and msg text to client
 * int *read_eof: eof flag
 * int *error: error flag
 *
 * Return: 0 when error, 1 continue
 */
int talk_to_client(int sock, FILE *fp, int *read_eof, int *error) {
	char byte[1];
	
	printf("Waiting for client request\n");
	if (packet_handling(sock, error)) {
		return 0;
	}
	
	// job type
	if (fread(byte, 1, 1, fp) == 0) {
		fprintf(stderr, "%sEOF%s\n", "\x1B[31m", "\x1B[0m");
		*read_eof = 1;
		byte[0] = 'Q';
	}
	
	if (send(sock, byte, 1, 0) == -1) {
		perror("send()");
		return 0;
	}
	
	if (packet_handling(sock, error)) {
		return 0;
	}
	
	// text length
	if (fread(byte, 1, 1, fp) == 0) {
		fprintf(stderr, "%sEOF%s\n", "\x1B[31m", "\x1B[0m");
		byte[0] = 0;
	}

	if (send(sock, byte, 1, 0) == -1) {
		perror("send()");
		return 0;
	}
	
	if (packet_handling(sock, error)) {
		return 0;
	}
	
	unsigned char len = byte[0];
	// send text
	for (int i=len; i>0; i--) {
		if (fread(byte, 1, 1, fp) == 0) {
			fprintf(stderr, "%sEOF%s\n", "\x1B[31m", "\x1B[0m");
			byte[0] = 0;
		}

		if (send(sock, byte, 1, 0) == -1) {
			perror("send()");
			return 0;
		}
		if (packet_handling(sock, error)) {
			return 0;
		}
	}
	return 1;
}
