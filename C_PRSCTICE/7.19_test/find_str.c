#include <stdio.h>

char *search_substr(const *src,const char *substr)
{
	if (NULL == src || NULL == substr) 
	{
		return NULL;
	}
				
	int i;
	int j;
	int temp;
							
	char *p = (char *)src;
								
	for (i = 0; p[i] != '\0'; i++) 
	{
		temp = i;
		j = 0;
													
		while (p[i++] == substr[j++])
		{
			if ('\0' == substr[j])	
			{
				return &p[temp];
			}
		}
		i = temp;
	}
	return NULL;

}

int main()
{
	char str1[100] = {0};
	char str2[100] = {0};

	printf("input str:\n");
	printf("str1 = ");
	scanf("%s",str1);
	printf("str2 = ");
	scanf("%s",str2);

	printf("%p\n",search_substr(str1,str2));
}
