#include <stdio.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <string.h>

#include "socket.h"

/* Create socket
 * 
 * Parameters:
 * int x:       1 if socket is for client, 0 when socket is for server
 * char *ip:    IP client will connect to
 * char *port:  Port socket will listen/connect to
 *
 * Return:
 * File descriptor for socket
 */
int create_socket(int x, char *ip, char *port) {
	int sock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	if (sock == -1) {
		perror("socket()");
		return -1;
	}
	
	short port_num = atoi(port);
	if (port_num == 0) {
		fprintf(stderr, "Invalid port.\n");
		return -1;
	}

	struct sockaddr_in server_addr;
	memset(&server_addr, 0, sizeof(struct sockaddr_in));
	server_addr.sin_family = AF_INET;
	server_addr.sin_port = htons(port_num);
	
	
	if (x) { // client
		if (inet_pton(AF_INET, ip, &server_addr.sin_addr.s_addr) != 1) {
			perror("inet_pton()");
			close(sock);
			return -1;
		}
	
		if (connect(sock, (struct sockaddr *)&server_addr, sizeof(server_addr))) {
			perror("connect()");
			close(sock);
			return -1;
		}
	}
	else { // server
		int yes = 1;
		if (setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &yes, sizeof(int))) {
			perror("setsockopt()");
			close(sock);
			return -1;
		}
	
		if (bind(sock, (struct sockaddr *)&server_addr, sizeof(server_addr))) {
			perror("bind()");
			close(sock);
			return -1;
		}

		printf("Bound to port %d!\n", port_num);

		if (listen(sock, SOMAXCONN)) {
			perror("listen()");
			close(sock);
			return -1;
		}
	}
	return sock;
}
