void calc_float(float a, float b){

	float result;
	
	if(a>b && b != 0){
		result = a/b;
	}else if(b>a && a != 0) {
		result =b/a;
	}
	else {
		result = 0;
	}
	
	return result;
}