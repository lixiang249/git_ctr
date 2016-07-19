#include <stdio.h>

void findmax(int s[], int t, int *k);

int main()
{
	int a[10] = {10,2,23,43,7,24,55,98,16,60};
	int k;

	findmax(a,10,&k);
	printf("max is:%d  k is: %d",a[k],k);
	printf("\n");
	return 0;
}

void findmax(int s[],int t, int *k)
{
	int i,max;
	for(max =i =0;i<t;i++)
		if(s[max] < s[i])
		{
			max = i;
			*k = max;
		}
	return;
}
