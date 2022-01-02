int foo(int N, int *a, int *b, int *c, int *d) {
	double tmp, total = 0;
	#pragma omp parallel
	{
		// #pragma omp section directive cannot be used alone.
		// It must be in the area of the #pragma omp sections directive.
		#pragma omp sections
		{
			#pragma omp section
			{
				#pragma omp for
				for (int i = 0; i <N; i++) {
					a[b[i]] += b[i];
					total += b[i];
				}
			}
			#pragma omp section
			{
				#pragma omp for
				for (int i = 0; i < N; i++) {
					tmp = c[i];
					c[i] = d[i];
					total += c[i];
				}
			}
			#pragma omp section
			{
				#pragma omp for
				for (int i = 0; i < N; i++) {
					total += d[i];
				}
			}
		}
	}
	return total;
}
