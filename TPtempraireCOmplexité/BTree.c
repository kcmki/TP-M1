#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "BTree.h"
 

void errorB(char *s){
	fprintf(stderr, "%s", s);
	exit(EXIT_FAILURE);
}

/*****************************************************************************/
/**************************** fonctions de base ******************************/
/*****************************************************************************/

BTree makeEmptyBTree(void){
	return (BTree)NULL;	
}

BTree makeNode(Element e, BTree l,  BTree r){
	BTree new;
	if ((new=(BTree)malloc(sizeof(Node)))==NULL) errorB("Allocation ratée!");
	new->elem=e;
	new->left=l;
	new->right=r;
	return new;
}

BTree makeLeaf(Element e){
	return makeNode(e,makeEmptyBTree(),makeEmptyBTree());
}


int isEmptyBTree(BTree bt){
	return (bt==NULL);
}

Element root(BTree bt){
	if(isEmptyBTree(bt)) errorB("Pas de noeud à la racine d'un arbre vide!!!");
	return bt->elem;
}


BTree leftChild(BTree bt){
	if(isEmptyBTree(bt)) errorB("Pas de fils gauche dans un arbre vide!!!");
	return bt->left;
}

BTree rightChild(BTree bt){
	if(isEmptyBTree(bt)) errorB("Pas de fils droit dans un arbre vide!!!");
	return bt->right;
}

int isLeaf(BTree bt){
	return !isEmptyBTree(bt) && isEmptyBTree(leftChild(bt)) && isEmptyBTree(rightChild(bt));
}

void freeNode(Node *c){
	free(c);
}

/*****************************************************************************/
/*************************** fonctions bas niveau ****************************/
/*****************************************************************************/

void insertRight(Node *n, Element e){
	if(!isEmptyBTree(n) && isEmptyBTree(rightChild(n)))
		n->right=makeLeaf(e);
	else errorB("insertRight impossible!");
}

void insertLeft(Node *n, Element e){
	if(!isEmptyBTree(n) && isEmptyBTree(leftChild(n)))
		n->left=makeLeaf(e);
	else errorB("insertLeft impossible!");
}

Element deleteRight(Node *n){
	if(isEmptyBTree(n) || !isLeaf(rightChild(n)))
		errorB("deleteRight imossible!");
		
	Element res=root(n->right);
	n->right=makeEmptyBTree();
	return res;
}

Element deleteLeft(Node *n){
	if(isEmptyBTree(n)  || !isLeaf(leftChild(n)))
		errorB("deleteLeft imossible!");
		
	Element res=root(n->left);
	n->left=makeEmptyBTree();
	return res;
}

void insertRightmostNode(BTree *bt, Element e){
	if(isEmptyBTree(*bt))
		*bt=makeLeaf(e);
	else{
		BTree tmp=*bt;
		while(!isEmptyBTree(rightChild(tmp)))
			tmp=rightChild(tmp);
		insertRight(tmp,e);
	}
}

Element deleteLeftmostNode(BTree *bt){
	Element res;
	if(isEmptyBTree(*bt))
		errorB("deleteLeftmostNode imossible!");
	if(isEmptyBTree(leftChild(*bt))){
		res=root(*bt);
		*bt=rightChild(*bt);
	}
	else{
		BTree tmp=*bt;
		while(!isEmptyBTree(leftChild(leftChild(tmp))))
			tmp=leftChild(tmp);
		res=root(leftChild(tmp));
		tmp->left=(tmp->left)->right;
	}
	return res;
}
