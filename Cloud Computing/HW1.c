//
// 2021 Fall - Cloud Computing (01)
// Homework #1: Parallelize matrix-vector multiplication, 
// 		and max operation using OpenMPI
//
// Copyright (c) Prof. Jaehyeong Sim
// Department of Computer Science and Engineering, Ewha Womans University


#include "mpi.h"
#include <stdio.h>

#define N 16
//#define N 1024

double mat[N][N];
double vec[N];

int main (int argc, char* argv[])
{
	int rank, size;
	int start_iter, end_iter, nlocal;
	double max;
	double t1, t2;
	char filename[16];
	double out[N];
	MPI_Status status;
	double rdata;

	// Read matrix and vector data from file
	sprintf(filename, "%d.txt", N);
	FILE *fp = fopen(filename, "r");
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			fscanf(fp, "%lf", &mat[i][j]);
		}
	}
	for (int i = 0; i < N; i++) {
		fscanf(fp, "%lf", &vec[i]);
	}
	fclose(fp);

	// MPI start
	MPI_Init(&argc, &argv);
	t1 = MPI_Wtime();

	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &size);
	
	nlocal = N/size;
	start_iter = nlocal * rank;
	end_iter = start_iter + nlocal;

	for (int i = start_iter; i < end_iter; i++) {
	       out[i] = 0.0;
	       for (int j = 0; j < N; j++) 
		       out[i] += mat[i][j] * vec[j];
	}

	if (rank == 0) {
		for (int i = 1; i < size; i++) {
			MPI_Recv(&rdata, 1, MPI_DOUBLE, i, 0, MPI_COMM_WORLD, &status);
			for(int j = nlocal*i; j < nlocal*(i+1); j++) {
				out[j] = 0.0;
				out[j] += rdata;
			}
		}
		max = out[0];
		for (int i = 1; i < N; i++){
			if (out[i] > max)
				max = out[i];
		}
		printf("Final maximum value is %lf\n", max);
	}

	else {
		for (int i = start_iter; i < end_iter; i++)
			MPI_Send(&out[i], 1, MPI_DOUBLE, 0, 0, MPI_COMM_WORLD);
	}

	t2 = MPI_Wtime();
	if (rank == 0)
		printf("Elapsed time for rank 0 is %f\n", t2-t1);

	if (rank == 1)
		printf("elapsed time for rank 1 is %f\n", t2-t1);
	
	MPI_Finalize();

	return 0;
}
