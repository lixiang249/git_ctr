#include <stdio.h>

char *str_covert(char *str);

int main()
{
	char str[128] = {0};
	printf("please input string:\n");
	printf("str = ");
	scanf("%s",str);

	printf("%s\n",str_covert(str));
	return 0;
}

char *str_covert(char *str)
{
	char *tmp = str;
	if( str == NULL)
	{
		return NULL;
	}
	else
	{	
		while(*tmp != '\0')
		{
			if((*tmp >='A') && (*tmp <= 'Z'))
			{
				*tmp = *tmp + 'a' - 'A';
				tmp++;
			}	
			 if((*tmp >= 'a') && (*tmp <= 'z'))
				{
					*tmp = *tmp + 'A' - 'a';
					tmp++;
				}
		}
	}
	
	return str;

}
