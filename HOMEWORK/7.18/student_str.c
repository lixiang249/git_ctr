#include <stdio.h>

typedef struct student {
						char name[128];
						char sex[10];
						int age;
						char tel[12];
						char addr[128];
}STUDENT_T;

int main()
{
	int i;

	STUDENT_T stu[10] = {{"lixiang","boy",21,"18408244767","sichuan-guanyuan"},
						 {"yangliling","boy",21,"18215445253","chengdu"},
						 {"huxiaoan","boy",22,"18408281101","hebei"}};

#if 1
	STUDENT_T *p = stu;

	for(i = 0; i < 3; i++)
	{
		printf("name:%s sex: %s age: %d tel: %s addr: %s \n",
				p[i].name,p[i].sex,p[i].age,p[i].tel,p[i].addr	);
	}
#endif
	return 0;

}
