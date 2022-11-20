#include "BTree.h"
#include "stdio.h"
#include "stdlib.h"

int equalBTreesREC(BTree bt1, BTree bt2){
    if(bt1 == NULL && bt2 == NULL){
        return 1;
    }
    if(bt1 != NULL && bt2 != NULL){
        if(bt1->elem == bt2->elem){
            return 1 * equalBTreesREC(bt1->left,bt2->left) * equalBTreesREC(bt1->right,bt2->right);
        }else{
            return 0;
        }
    }
    return 0;
}
int isSubBTreeREC(BTree sbt, BTree bt){
    if(sbt == NULL){
        printf("sous arbre vide erreur");
        exit(-1);
    }
    if(bt == NULL) return 0;
    return equalBTreesREC(sbt,bt) + isSubBTreeREC(sbt,bt->right) + isSubBTreeREC(sbt,bt->left);
}

int equalBTreesIter(BTree bt1, BTree bt2){
    BTree Pile1[100] = {NULL};
    BTree Pile2[100] = {NULL};
    int iP = -1;

    if(bt1 == NULL && bt2 == NULL) return 1;
    
    while(bt1 != NULL && bt2 != NULL){
        if(bt1->right != NULL && bt2->right != NULL){
            iP++;
            Pile1[iP] = bt1->right;
            Pile2[iP] = bt2->right;
        }

        if(bt1->elem == bt2->elem){
            if(bt1->left != NULL && bt2->left != NULL){
                bt1 = bt1->left;
                bt2 = bt2->left;
            }
            else if(bt1->left == NULL && bt2->left == NULL){
                if(iP == -1) return 1;
                else{
                    bt1 = Pile1[iP];
                    bt2 = Pile2[iP];
                    iP--;
                }
            }else{
                return 0;
            }
        }else{
            return 0;
        }
    }
    return 0;
}

int isSubBTreeIter(BTree sbt, BTree bt){

    BTree Pile1[100] = {NULL};
    int iP = -1;

    if(sbt == NULL){
        printf("sous arbre vide erreur");
        exit(-1);
    }
    if(bt == NULL) return 0;

    while(bt != NULL){
        if(bt->right != NULL){
            iP++;
            Pile1[iP] = bt->right;
        }
        if(equalBTreesIter(bt,sbt)) return 1;
        else{
            if(bt->left != NULL){bt = bt->left;}
            else{
                if(iP == -1) return 0;
                bt = Pile1[iP];iP--;
                }
        }
    }

}



int main(int argc,char **argv){
    BTree B1,B2;
    B1 = makeLeaf(5);
    B2 = makeLeaf(5);

    //creation B1
    insertRight(B1,8);
    insertLeft(B1,2);
        insertRight(B1->left,3);
        insertLeft(B1->left,1);

        insertRight(B1->right,9);
        insertLeft(B1->right,7);
    //creation B2
    insertRight(B2,8);
    insertLeft(B2,2);
        insertRight(B2->left,3);
        insertLeft(B2->left,1);

        insertRight(B2->right,9);
        insertLeft(B2->right,7);

    //test des fonctions
    printf("result %d",isSubBTreeIter(B1->right,B2));
}