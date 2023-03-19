import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from numpy import random, sqrt
from sklearn.neural_network import MLPClassifier
from sklearn.model_selection import train_test_split
from sklearn import svm
from sklearn.tree import DecisionTreeClassifier
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import classification_report, confusion_matrix
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import KFold
from sklearn.cluster import KMeans


X = np.genfromtxt('data.csv', delimiter=',', dtype=int)
X.shape
df = pd.read_csv("data.csv",nrows=5000)

Y = np.genfromtxt('labels.csv', delimiter=',', dtype=int)
Y[Y==10] = 0
Y.shape
print(np.unique(Y))


plt.figure(figsize=(15,8))
for i in range(13): #note: each 500 lines, there is a new value
    c = random.randint(X.shape[0]) #pick a random line from the dataset 
    a = X[c,:].reshape((20, 20)) #shape of one number in the image 
    a=np.transpose(a) # la transposé (rendre les ligne des colonne et vice versa)
    plt.subplot(1,13,i+1)
    plt.title('label '+ str(Y[c]))
    plt.imshow(a,cmap='gray')


scaler = StandardScaler()
scaler.fit(X)
scaled_features = scaler.transform(X)
Xt, Xtt, Yt, Ytt = train_test_split(
      scaled_features, Y, test_size = 0.30)
def eucledian(xt,x):
    dist = []
    for i in range(0,len(xt)):
        dist.append(sqrt((xt[i]-x[i])*(xt[i]-x[i])))
    return dist
def proba(labels):
    prob = []
    print(labels)
    for i in labels:
        prob[labels[i]] += 1
    print(prob)
    return prob
def predict(x_train, y , x, k):

    #Array to store distances
    point_dist = []
    #Loop through each training Data
    for j in range(len(x_train)): 
        distances = eucledian(np.array(x_train[j,:]) , x) 
        #print(np.array(x_train[j,:]))
        #Calculating the distance
        point_dist.append(distances)
    point_dist = np.array(point_dist) 
      
    #Sorting the array while preserving the index
    #Keeping the first K datapoints
    dist = np.argsort(point_dist)[:k] #argsort trej3lk indices
    #print(point_dist[dist])
          
    #Labels of the K datapoints from above
    labels = y[dist]
    #print("labels",labels)   
      
    #Majority voting

    return proba(labels)

def svmtrain(Xt, Yt):
    #Créer le modèle
    model= svm.SVC(kernel='linear') 
    # entrainement 
    model.fit(Xt, Yt)
    return model
def knntrain(Xt, Yt):
    #Créer le modèle
    model= KNeighborsClassifier(n_neighbors=7) 
    # entrainement 
    model.fit(Xt, Yt)
    return model
def treetrain(Xt, Yt):
    #Créer le modèle
    model= DecisionTreeClassifier() 
    # entrainement 
    model.fit(Xt, Yt)
    return model
def mlptrain(Xt, Yt):
    #Créer le modèle
    model= MLPClassifier(solver='sgd', alpha=1e-5, hidden_layer_sizes=(25),max_iter=100000) 
    # entrainement 
    model.fit(Xt, Yt)
    return model
class ConfMatrix:
    def __init__(self, ypred, Ytt):
        self.matrice = np.matrix(np.zeros((10,10)))
        for i in range(0, len(Ytt)):
            self.matrice[ypred[i],Ytt[i]] += 1
    def __str__(self):
        return str(self.matrice)
    def rappelCalcul(self, i):
        TP,TN,FP,FN = 0,0,0,0
        for i in range(0,10):
            for j in range(0,10):
                if i == j:
                    TP += self.matrice[i,j]
                else:
                    FN += self.matrice[i,j]
                    FP += self.matrice[j,i]
    
# Prediction
#model = svmtrain(Xt,Yt)
#y_pred = model.predict(Xtt)
predict(Xt,Yt,Xtt[0],10)
#mat = ConfMatrix(y_pred, Ytt)
#print(mat)
#print(y_pred)
