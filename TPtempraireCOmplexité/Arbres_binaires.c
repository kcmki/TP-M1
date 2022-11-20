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
    printf("result %d",isSubBTreeREC(B1->left,B2));
}