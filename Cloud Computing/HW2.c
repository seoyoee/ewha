//
// 2021 Fall - Cloud Computing (01)
// Homework #2: Pthread Programming
//
// Copyright (c) Prof. Jaehyeong Sim
// Department of Computer Science and Engineering, Ewha Womans University
//

#include <stdio.h>
#include <string.h>
#include <pthread.h>

#define STR_SIZE 128

char original_string[STR_SIZE] = "Computer, Ewha";

void* get_str(void *data)
{
	pthread_t tid;
	tid = pthread_self();
	int i;
	char* target_string = (char*) data;
	char str[strlen(target_string)];
	int num = strlen(original_string) - strlen(target_string);

	for(i = 0; i < strlen(target_string); i++){
		str[i] = target_string[i];
		printf("Iteration %d:\tthread %d\tgot %c\tfrom\t%s\n", i, (int)tid, str[i], original_string);
		if (target_string[i] == original_string[i])
			original_string[i] = '_';
		else
			original_string[num+i] = '_';
	}
	printf("*** Thread %d made a string: %s\n", (int)tid, str);
	pthread_exit(NULL);
}


int main() {

	char target_string1[STR_SIZE] = "Ewha";
	char target_string2[STR_SIZE] = "Computer";

	printf("*** The initial original string:\t\t%s\n", original_string);

	pthread_t threads[2];
	int rc;

	rc = pthread_create(&threads[0], NULL, get_str, (void *)target_string1);
	if (rc < 0){
		printf("ERROR; return code from pthread_create() is %d\n", rc);
		pthread_exit(NULL);
	}

	rc = pthread_create(&threads[1], NULL, get_str, (void *)target_string2);
	if (rc < 0){
		printf("ERROr; return code from pthread_create() is %d\n", rc);
		pthread_exit(NULL);
	}

	pthread_join(threads[0], NULL);
	pthread_join(threads[1], NULL);

	printf("*** Final original string:\t\t\t%s\n", original_string);
	return 0;

}
