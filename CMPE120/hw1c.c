#include <stdio.h>

typedef unsigned char *pointer;
void show_bytes(pointer start, size_t len);

int main(int argc, char const *argv[])
{
	printf("Endianness demo made by Irina Voronova on %s %s \n", __DATE__ , __TIME__);

	unsigned int myint = 0xABCD1234;
	printf("The adress of myint is %p \n",&myint);
	printf("inta = %u (0x%08x)\n", myint, myint);
	show_bytes((pointer) &myint, sizeof(myint));
	printf("This is little endian machine, the smaller numbers come first\n");

	return 0;
}

void show_bytes(pointer start, size_t len){
	size_t i;
	for (i= 0; i< len; i++)
		printf("%p\t0x%.2x\n",start+i, start[i]);

	printf("\n");

}