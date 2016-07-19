#include <stdio.h>
#include <string.h>

char *merge_str(char *str1, char *str2);

int main()
{
	char str1[100] = {0};
	char str2[100] = {0};

	printf("Please input two strings:\n");
	printf("string1 = ");
	scanf("%s",str1);
	printf("string2 = ");
	scanf("%s",str2);
//	printf("line: %d",__LINE__);
	
	printf("The merge after string is:\n");
	printf("   %s\n",merge_str(str1,str2));

	return 0;
}

char  *merge_str(char *str1, char *str2)
{
	int len = 0;
	int i;
	int j;
	char tmp;

//	printf("line: %d",__LINE__);
	strcat(str1,str2);
//	printf("line: %d",__LINE__);
	len = strlen(str1);
//	printf("line: %d",__LINE__);
	for(i = 0; i < len-1; i++ )
	{
		for(j = 0; j < len-1-i; j++)
		{
			if(str1[j]>str1[j+1])
			{
				tmp = str1[j];
				str1[j] = str1[j+1];
				str1[j+1] = tmp;
			}
		}
	}

	return str1;
}
