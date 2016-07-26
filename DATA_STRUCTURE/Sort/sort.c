/*************************************************************************
	> File Name: sort.c
	> Author: 
	> Mail: 
	> Created Time: 2016年07月26日 星期二 17时22分30秒
 ************************************************************************/

#include <stdio.h>
#include "sort.h"

int main()
{
    int i;
#if 1
    data_t a[10] = {1,23,3,25,55,23,12,32,9,47};
    insert_sort_2(a,10);
    for(i = 0; i < 10; i++)
    {
        printf("%d ",a[i]);
    }
    printf("\n");
#endif

#if 1
    data_t b[10] = {1,23,3,25,55,23,12,32,9,47};
    insert_sort_2(b,10);
    for(i = 0; i < 10; i++)
    {
        printf("%d ",b[i]);
    }
    printf("\n");
#endif

#if 1
    data_t c[10] = {1,2,56,32,5,7,21,4,66,78};
    quick_sort(c,1,10);
    for(i = 0; i < 10; i++)
    {
        printf("%d ",c[i]);
    }
    printf("\n");
#endif

#if 1
    data_t d[10] = {23,34,45,1,21,34,32,5,6,19};
    half_insert_sort(d,10);
    for(i = 0; i < 10; i++)
    {
        printf("%d ",d[i]);
    }
    
    printf("\n");
#endif


    return 0;

}


void insert_sort_1(data_t *a, int len)
{
    int i;
    int j;
    int tmp;

    for(i = 1; i < len; i++)
    {
        tmp = a[i];//保存ai，之后会被覆盖
        for(j = i-1; j >= 0; j--)//j指示比较找位置的次数
        {
            if(a[j] > tmp)//若aj大于tmp，就将aj向后移动
            {
                a[j+1] = a[j];
            }
            else
            {
                break;
            }
        }
        a[j+1] = tmp;
    }

    return;
}

void insert_sort_2(data_t *a, int len)
{
    int i;
    int j;
    int tmp;
    
    for(i = 1; i < len; i++)
    {
        if(a[i] < a[i-1])
        {
            tmp = a[i];
            for(j = i-1; j >= 0 && a[j] > tmp; j--)
            {
                a[j+1] = a[j];
            }
            a[j+1] = tmp;
        }
    }
}

void quick_sort(data_t *a, int start, int end)
{
    if(start < end)
    {
        int i = start;
        int j = end;
        int tmp = a[i];
        while(i < j)
        {
            while(a[j] >= tmp && i < j)
            {
                j--;
            }
            if(i < j)
            {
                a[i] = a[j];
            }
            while(a[i] <= tmp && i < j)
            {
                i++;
            }
            if(i < j)
            {
                a[j] = a[i];
            }
        }

        a[i] = tmp;

        quick_sort(a,start,i-1);
        quick_sort(a,i+1,end);
        
    }

    return;
}
#if 1 
void half_insert_sort(int *key, int len)
{
    int i,j;
    int low,mid,high;
    int tmp;

    for(i = 1; i < len; i++)
    {
        low = 0;
        high = i-1;
        tmp = key[i];

        while(low <= high)
        {
            mid = (low + high)/2;
            if(tmp >= key[mid])
            {
                low = mid + 1;
            }
            else
            {
                high = mid - 1;
            }
        }

        for(j = i-1; j >= low; j-- )
        {
            key[j+1] = key[j];
        }

        key[low] = tmp;
    }
    return;
}
#endif

#if 0
void half_insert_sort(int *key, int len)
{
    int i, j;
    int low, mid, high;
    int tmp;
    for(i = 1; i < len; i++)
    {
        low = 0;
        high = i-1;
        tmp = key[i];

        while(low <= high)//折半查找
        {
            mid = (low+high)/2;
            if(tmp >= key[mid])
            {
                low = mid+1;
            }
            else
            {
                high = mid-1;
            }
        }

        for(j = i-1; j >= low; j--)//挪位置
        {
            key[j+1] = key[j];
        }

        key[low] = tmp;//插入
    }
}
#endif
