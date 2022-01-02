#include <stdio.h>
#include <opm.h>

void rot(int N, double* a) {
	#pragma omp parallel for
	{
		for (int i = 0; i < N-1; i++) {
			double tmp = a[i];
			a[i] = a[i+1];
			a[i+] = tmp;
		}
	}
}
