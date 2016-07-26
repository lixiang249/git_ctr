/*************************************************************************
	> File Name: sort.h
	> Author: 
	> Mail: 
	> Created Time: 2016年07月26日 星期二 17时19分15秒
 ************************************************************************/

#ifndef _SORT_H
#define _SORT_H

typedef int data_t;

//直接插入排序
void insert_sort_1(data_t *a, int len);
void insert_sort_2(data_t *a, int len);

//快速排序
void quick_sort(data_t *a, int start, int end);

//折半查找排序
void half_insert_sort(int *key, int len);

#endif
