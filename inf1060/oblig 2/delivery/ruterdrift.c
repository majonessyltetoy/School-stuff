#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct router {
	unsigned char flag;
	char model[254];
};

void menu(struct router **map, char *file);
void print_id_info(struct router **map);
int change_info(struct router **map, int i);
void add_router(struct router **map);
void delete_router(struct router **map);
void write_to_file(struct router **map, char *file);


int main(int argc, char *arg[]) {
	argc = argc;
	FILE *fp;
	int router_count;
	int c;            // counter
	unsigned char byte;
	unsigned char router_id;
	unsigned char flag;
	unsigned char model_length;
	char model[254];
	
	fp = fopen(arg[1], "r");
	fread(&router_count, sizeof(int), 1, fp);
	struct router *map[256]; // there are 256 possible router IDs
	
	// initiate map
	for (c = 0; c < 256; c++) {
		map[c] = NULL;
	}
	
	// read file
	for (c = 0; c < router_count; c++) {
		fread(&byte, 1, 1, fp); // remove newline
		fread(&router_id, 1, 1, fp);
		fread(&flag, 1, 1, fp);
		fread(&model_length, 1, 1, fp);
		fread(&model, 1, (model_length - 1), fp);
		model[model_length -1] = '\0';
		map[router_id] = malloc(255);
		map[router_id]->flag = flag;
		strcpy(map[router_id]->model, model);
	}
	fclose(fp);
	
	// display menu loop
	menu(map, arg[1]);
	
	// write to file
	write_to_file(map, arg[1]);
	
	// free memory
	for (c = 0; c < 256; c++) {
		if (map[c] != NULL) {
			free(map[c]);
		}
	}
	printf("Exiting\n");
	return 0;
}

void menu(struct router **map, char *file) {
	int c; // counter
	int choice; // user input number
	int change_id;
	
	printf("\nEnter a number\n\n");
	printf("\t1 - Display all taken IDs\n");
	printf("\t2 - Print router info\n");
	printf("\t3 - Change router info\n");
	printf("\t4 - Add new router to database\n");
	printf("\t5 - Delete router from database\n");
	printf("\t6 - Save file changes\n");
	printf("\t7 - Save and quit\n");
	printf("> ");
	scanf("%d%*c", &choice);
	
	if (!(choice > 0 && choice < 8)) {
		printf("Inavalid input.\n");
		return;
	}
	
	switch(choice) {
		case 1:
			printf("ID:\n");
			for (c = 0; c < 256; c++) {
				if (map[c] != NULL) {
					printf("%i \t", c);
				}
			}
			printf("\n");
			break;
		case 2:
			print_id_info(map);
			break;
		case 3:
			change_id = change_info(map, -1);
			if (change_id != -1) {
				// update change counter
				map[change_id]->flag = (map[change_id]->flag & 0x0f)  | ((char)((map[change_id]->flag >> 4) + 1) << 4);
			}
			break;
		case 4:
			add_router(map);
			break;
		case 5:
			delete_router(map);
			break;
		case 6:
			write_to_file(map, file);
			break;
		case 7:
			return;
	}
	menu(map, file);
}

void print_id_info(struct router **map) {
	int id; // id to display
	
	printf("Enter ID for info\n> ");
	scanf("%d%*c", &id);
	
	if (!(id >= 0 && id < 256)) {
		printf("Invalid ID.\n");
		return;
	}
	
	if (map[id] == NULL) {
		printf("ID not in use.\n");
		return;
	}
	printf("ID:       %i\n", id);
	printf("Model:    %s\n", map[id]->model);
	printf("Active:   ");
	if (map[id]->flag & (1 << 0)) {
		printf("TRUE\n");
	}
	else {
		printf("FALSE\n");
	}
	printf("Wireless: ");
	if (map[id]->flag & (1 << 1)) {
		printf("TRUE\n");
	}
	else {
		printf("FALSE\n");
	}
	printf("5GHz:     ");
	if (map[id]->flag & (1 << 2)) {
		printf("TRUE\n");
	}
	else {
		printf("FALSE\n");
	}
	printf("Unused:   ");
	if (map[id]->flag & (1 << 3)) {
		printf("TRUE\n");
	}
	else {
		printf("FALSE\n");
	}
	printf("Change counter: %i\n", (int)(map[id]->flag >> 4));
}

int change_info(struct router **map, int i) {
	int id;
	int choice;
	char user_input[254];
	
	if (i != -1) {
		id = i;
	}
	else {
		printf("Enter router ID to change info\n> ");
		scanf("%d%*c", &id);
	}
	
	if (!(id >= 0 && id < 256)) {
		printf("ID outside range 0 to 255.\n");
		return -1;
	}
	if (map[id] == NULL) {
		printf("ID not in use.\n");
		return -1;
	}
	if ((int)(map[id]->flag >> 4) == 15) {
		printf("Change counter has reach limit\n");
		return -1;
	}
	
	printf("\nChange router ID: %i\n", id);
	printf("Enter a number to change the value\n\n");
	printf("\t1 - Active state\n");
	printf("\t2 - Wireless\n");
	printf("\t3 - 5GHz support\n");
	printf("\t4 - Unused\n");
	printf("\t5 - Model name\n");
	printf("\t6 - Exit and save\n");
	printf("> ");
	scanf("%d%*c", &choice);
	
	if (!(choice > 0 && choice < 7)) {
		printf("Inavalid input.\n");
		printf("Flag changes saved, and exiting.");
		return id;
	}
	
	switch(choice) {
		case 1:
			printf("Active:   ");
			if (map[id]->flag & (1 << 0)) {
				printf("TRUE\n");
			}
			else {
				printf("FALSE\n");
			}
			printf("Router in use? [y/n]: ");
			scanf("%s", user_input);
			if (user_input[0] == 'y' || user_input[0] == 'Y') {
		
				map[id]->flag = map[id]->flag | 0x01;
			}
			else {
				map[id]->flag = map[id]->flag & ~0x01;
			}
			change_info(map, id);
			break;
		case 2:
			printf("Wireless: ");
			if (map[id]->flag & (1 << 1)) {
				printf("TRUE\n");
			}
			else {
				printf("FALSE\n");
			}
			printf("Is router wireless? [y/n]: ");
			scanf("%s", user_input);
			if (user_input[0] == 'y' || user_input[0] == 'Y') {
		
				map[id]->flag = map[id]->flag | 0x02;
			}
			else {
				map[id]->flag = map[id]->flag & ~0x02;
			}
			change_info(map, id);
			break;
		case 3:
			printf("5GHz:     ");
			if (map[id]->flag & (1 << 2)) {
				printf("TRUE\n");
			}
			else {
				printf("FALSE\n");
			}
			printf("Does router support 5GHz? [y/n]: ");
			scanf("%s", user_input);
			if (user_input[0] == 'y' || user_input[0] == 'Y') {
		
				map[id]->flag = map[id]->flag | 0x04;
			}
			else {
				map[id]->flag = map[id]->flag & ~0x04;
			}
			change_info(map, id);
			break;
		case 4:
			printf("Unused:   ");
			if (map[id]->flag & (1 << 3)) {
				printf("TRUE\n");
			}
			else {
				printf("FALSE\n");
			}
			printf("Is router unused? [y/n]: ");
			scanf("%s", user_input);
			if (user_input[0] == 'y' || user_input[0] == 'Y') {
		
				map[id]->flag = map[id]->flag | 0x08;
			}
			else {
				map[id]->flag = map[id]->flag & ~0x08;
			}
			change_info(map, id);
			break;
		case 5:
			printf("Current name: %s\n", map[id]->model);
			printf("New model name: ");
			scanf("%253[^\n]", user_input);
			strcpy(map[id]->model, user_input);
			printf("Name successfully changed!\n");
			change_info(map, id);
		case 6:
			return id;
	}
	return id;
}

void add_router(struct router **map) {
	int id;
	char flag = 0x00;
	char user_input[254];
	
	printf("Enter ID to add\n> ");
	scanf("%d%*c", &id);
	
	if (!(id >= 0 && id < 256)) {
		printf("ID outside range 0 to 255.\n");
		return;
	}
	if (map[id] != NULL) {
		printf("ID is used.\n");
		return;
	}
	
	map[id] = malloc(255);
	
	printf("Model name: ");
	scanf("%253[^\n]", user_input);
	strcpy(map[id]->model, user_input);
	
	printf("Router in use? [y/n]: ");
	scanf("%s", user_input);
	if (user_input[0] == 'y' || user_input[0] == 'Y') {
		
		flag = flag | 0x01;
	}
	
	printf("Is router wireless? [y/n]: ");
	scanf("%s", user_input);
	if (user_input[0] == 'y' || user_input[0] == 'Y') {
		flag = flag | 0x02;
	}
	
	printf("5GHz support? [y/n]: ");
	scanf("%s", user_input);
	if (user_input[0] == 'y' || user_input[0] == 'Y') {
		flag = flag | 0x04;
	}
	
	printf("Router unused? [y/n]: ");
	scanf("%s", user_input);
	if (user_input[0] == 'y' || user_input[0] == 'Y') {
		flag = flag | 0x08;
	}
	map[id]->flag = flag;
	printf("\nRouter added!\n\n");
}
void delete_router(struct router **map) {
	int id;
	
	printf("Enter ID to delete\n> ");
	scanf("%d%*c", &id);
	
	if (!(id >= 0 && id < 256)) {
		printf("ID outside range 0 to 255.\n");
		return;
	}
	if (map[id] == NULL) {
		printf("ID not in use.\n");
		return;
	}
	
	free(map[id]);
	map[id] = NULL;
	printf("\nRouter removed.\n\n");
}

void write_to_file(struct router **map, char *file) {
	FILE *fp;
	int router_count;
	int c;
	int tmp;
	
	fp = fopen(file, "w");
	
	router_count = 0;
	for (c = 0; c < 256; c++) {
		if (map[c] != NULL) {
			router_count++;
		}
	}
	
	fwrite(&router_count, 1, sizeof(router_count), fp);
	for (c = 0; c < 256; c++) {
		if (map[c] != NULL) {
			tmp = strlen(map[c]->model) + 1;
			fwrite("\n", 1, 1, fp);
			fwrite(&c, 1, 1, fp);
			fwrite(&map[c]->flag, 1, 1, fp);
			fwrite(&tmp, 1, 1, fp); // model length
			for (int n=0; n < tmp - 1; n++) {
				fwrite(&map[c]->model[n], 1, 1, fp);
			}
		}
	}
	fwrite("\n", 1, 1, fp);
	
	fclose(fp);
	printf("Writing to file\n");
}
	
	

