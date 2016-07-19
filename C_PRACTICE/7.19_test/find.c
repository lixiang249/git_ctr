#include <stdio.h>

char *mystrstr(const char *src, const char *str)
{
	char *p = NULL;

	while(*src)
	{	

		if(*src == *str)
		{
			p = src;
			while(*src++ == *str++);
			if(*(str - 1) == '\0')
			{
				return p;
			}
		}
		else 
		{
			src++;
		}

	}
	
	return NULL;

}
int main()
{
	char str[100] = {0};
	char src[100] = {0};

	printf("input strinf:\n");
	printf("src = ");
	scanf("%s",src);
	printf("str = ");
	scanf("%s",str);
	
	printf("the addr is : %p\n",mystrstr(src,str));

	return 0;
}
