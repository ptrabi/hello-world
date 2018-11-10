#include <stdio.h>
#include <string.h>

void main(int argc, char *argv[])
{
	FILE *Fin, *Fout;
	char p, c, K[100];
	int i, n;
	Fin = fopen(argv[1], "rb");
	if (Fin == NULL){
		printf("Kesalahan dalam membuka %s sebagai berkas masukan/n", argv[1]);
	}else{
		Fout = fopen(argv[2], "wb");
		printf("\nEnkripsi %s menjadi %s ...\n", argv[1], argv[2]);
		printf("\n");
		printf("Kata kunci : ");
		scanf("%s", K);
		n = strlen(K); /*panjang kunci*/
		i = 0;
	}
	while ((p = getc(Fin)) != EOF) {
	c = p ^ K[i]; /* operasi XOR */
	putc(c, Fout);
	i++;
	if (i > (n -1)) {
	i = 0;		
		}
	}	
fclose(Fin);
fclose(Fout);
}
