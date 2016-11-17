// ANALIZATOR LEKSYKALNY
//
// Wczytuje ze standardowego wej�cia ci�g znak�w.
// Drukuje na standardowe wyj�cie podzia� tego ci�gu na leksemy.


#include<stdio.h>
#include<stdlib.h>

#define max_dl_leksemu 100

typedef enum { FALSE = 0, TRUE = 1 }  Boolean;

typedef enum { STAN_POCZ   =  0,
               STAN_B      =  1,
               STAN_BE     =  2,
               STAN_E      =  3,
               STAN_EN     =  4,
               STAN_LICZ   =  5,
               STAN_CIACH  =  6,
               STAN_CGW    =  7,
               STAN_CGWGW  =  8,
               STAN_SEPAR  =  9,
               STAN_KLUCZ  = 10,
               STAN_OPER   = 11,
               STAN_KOMENT = 12,
               STAN_NIEOKR = 13  }  Stany;

typedef enum { ZNAK_B      =  0,
               ZNAK_E      =  1,
               ZNAK_G      =  2,
               ZNAK_N      =  3,
               ZNAK_D      =  4,
               ZNAK_PL     =  5,
               ZNAK_GW     =  6,
               ZNAK_CYF    =  7,
               ZNAK_CIACH  =  8,
               ZNAK_SP     =  9,
               ZNAK_NIEOKR = 10  }  Znaki;

typedef enum { KLUCZ  = 0,
               OPER   = 1,
               LICZ   = 2,
               KOMENT = 3,
               SEPAR  = 4 }  Jednostki;

struct leksem
  {
    Jednostki jed;             // rodzaj leksemu
    int dlug;                  // d�ugo�� leksemu  ( 1 <= dlug <= max_dl_leksemu )
    char zaw[max_dl_leksemu];  // chary sk�adaj�ce si� na leksem
  };


void  blad (char s[], char ch)
    // sygnalizacja b��du; drukuje napis  s  i char  ch
    // a nast�pnie si� zatrzymuje
  {
    printf ("!!! %s  '%c' !!!\n\n", s, ch);
    exit(1);
  }


Znaki  kategoria (char ch)
    // ta funkcja grupuje chary z wej�cia w elementy typu  Znaki ;
    // to znaczy zapomina o r��nicy mi�dzy literami ma�ymi i du�ymi,
    // wszystkim cyfrom przypisuje  ZNAK_CYF ,
    // nielegalnym znakom przypisuje  ZNAK_NIEOKR
  {
    if (ch == 'b' || ch == 'B')  return ZNAK_B;     else
    if (ch == 'e' || ch == 'E')  return ZNAK_E;     else
    if (ch == 'g' || ch == 'G')  return ZNAK_G;     else
    if (ch == 'n' || ch == 'N')  return ZNAK_N;     else
    if (ch == 'd' || ch == 'D')  return ZNAK_D;     else
    if (ch == '+')               return ZNAK_PL;    else
    if (ch == '*')               return ZNAK_GW;    else
    if (ch == '/')               return ZNAK_CIACH; else
    if (ch == ' ')               return ZNAK_SP;    else
    if ('0' <= ch && ch <= '9')  return ZNAK_CYF;
    else  return ZNAK_NIEOKR;
  }


Stany  tab_symb[10][11];
  // -- tabela symboli czyli zakodowany automat; indeksowana stanami
  // od STAN_POCZ do STAN_SEPAR oraz znakami od ZNAK_B do ZNAK_NIEOKR

char  wejscie;
  // char przeczytany na zapas z wej�cia


void  leks (struct leksem* lk)
    // zasadnicza funkcja programu;
    // jej wywo�anie powoduje wczytanie odpowiedniej liczby char�w z
    // wej�cia i z�o�enie z nich leksemu (lub sygnalizacj� b��du);
    // zak�adamy, �e w momencie wywo�ania w zmiennej globalnej  wejscie
    // jest ju� wczytany jeden char z wej�cia;
    // po zako�czeniu dzia�ania tej funkcji w tej zmiennej jest ju�
    // wczytany jeden char na zapas
  {
    Stany  q, q1;
    Znaki  z;
    Boolean  koniec;
    q1 = STAN_POCZ; lk->dlug = 0; koniec = FALSE;
    do
      { z = kategoria(wejscie);
        q = q1; q1 = tab_symb[q][z];
        if (q1 == STAN_NIEOKR)  { /* b��d */ q1 = q; koniec = TRUE; }  else
        if (q1 > STAN_SEPAR)
          {   /* koniec leksemu */
            lk->zaw[lk->dlug] = wejscie; lk->dlug++; scanf("%c", &wejscie);
              /* Uwaga: brak sygnalizacji przepe�nienia tablicy  lk->zaw  */
            koniec = TRUE;
          }
        else
          { lk->zaw[lk->dlug] = wejscie; lk->dlug++; scanf("%c", &wejscie);
              /* Uwaga: brak sygnalizacji przepe�nienia tablicy  lk->zaw  */
          }
        }
    while (! koniec);
      // po zako�czeniu powy�szej p�tli w  q1  jest stan, na kt�rym
      // automat zako�czy� dzia�anie; pozostaje sprawdzi�, czy to jest
      // stan akceptuj�cy i co akceptuj�cy
    switch (q1)
      { case STAN_POCZ   : blad("Nieoczekiwany znak", wejscie); break;
        case STAN_B      : blad("Mialo byc 'beg' a jest", wejscie); break;
        case STAN_BE     : blad("Mialo byc 'beg' a jest", wejscie); break;
        case STAN_E      : blad("Mialo byc 'end' a jest", wejscie); break;
        case STAN_EN     : blad("Mialo byc 'end' a jest", wejscie); break;
        case STAN_CIACH  : blad("Mial byc komentarz a jest", wejscie); break;
        case STAN_CGW    : blad("Mial byc komentarz a jest", wejscie); break;
        case STAN_CGWGW  : blad("Mial byc komentarz a jest", wejscie); break;
        case STAN_LICZ   : lk->jed = LICZ; break;
        case STAN_SEPAR  : lk->jed = SEPAR; break;
        case STAN_KLUCZ  : lk->jed = KLUCZ; break;
        case STAN_OPER   : lk->jed = OPER; break;
        case STAN_KOMENT : lk->jed = KOMENT; break;
        case STAN_NIEOKR : blad("Nieoczekiwany znak", wejscie); break;
      }
  }


void  wydruk (struct leksem lk)
    // wydruk pojedynczego leksemu
  {
    int i;
    printf("LEKSEM:  ");
    switch (lk.jed)
      { case KLUCZ  : printf("klucz      '"); break;
        case OPER   : printf("operator   '"); break;
        case LICZ   : printf("liczba     '"); break;
        case KOMENT : printf("komentarz  '"); break;
        case SEPAR  : printf("separator  '"); break;
      }
    for (i=0; i<lk.dlug; i++)  printf("%c", lk.zaw[i]);
    printf("'\n");
  }


int main ()
  {
    //------------------------------------------------------
    // Inicjalizacja tabeli symboli:

    Stany q;  Znaki z;  struct leksem  lk;

    for (q = STAN_POCZ; q <= STAN_SEPAR; q++)
      for (z = ZNAK_B; z <= ZNAK_NIEOKR; z++)
        tab_symb[q][z] = STAN_NIEOKR;

    for (q = STAN_CGW; q <= STAN_CGWGW; q++)
      for (z = ZNAK_B; z <= ZNAK_NIEOKR; z++)
        tab_symb[q][z] = STAN_CGW;

    tab_symb[STAN_POCZ][ZNAK_B] = STAN_B;
    tab_symb[STAN_POCZ][ZNAK_E] = STAN_E;
    tab_symb[STAN_POCZ][ZNAK_PL] = STAN_OPER;
    tab_symb[STAN_POCZ][ZNAK_GW] = STAN_OPER;
    tab_symb[STAN_POCZ][ZNAK_CYF] = STAN_LICZ;
    tab_symb[STAN_POCZ][ZNAK_CIACH] = STAN_CIACH;
    tab_symb[STAN_POCZ][ZNAK_SP] = STAN_SEPAR;

    tab_symb[STAN_B][ZNAK_E] = STAN_BE;
    tab_symb[STAN_BE][ZNAK_G] = STAN_KLUCZ;
    tab_symb[STAN_E][ZNAK_N] = STAN_EN;
    tab_symb[STAN_EN][ZNAK_D] = STAN_KLUCZ;
    tab_symb[STAN_LICZ][ZNAK_CYF] = STAN_LICZ;

    tab_symb[STAN_CIACH][ZNAK_GW] = STAN_CGW;
    tab_symb[STAN_CGW][ZNAK_GW] = STAN_CGWGW;
    tab_symb[STAN_CGWGW][ZNAK_GW] = STAN_CGWGW;
    tab_symb[STAN_CGWGW][ZNAK_CIACH] = STAN_KOMENT;

    tab_symb[STAN_SEPAR][ZNAK_SP] = STAN_SEPAR;

    //------------------------------------------------------
    // Inne inicjalizacje:

    printf("\n"); scanf("%c", &wejscie); printf("\n");

    //------------------------------------------------------
    // Analiza leksykalna:

     while (wejscie != '\n')  { leks(&lk); wydruk(lk); }
     printf("\n");

    //------------------------------------------------------

     return 0;
  }
