import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from numpy import random
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
        self.total=0
        for i in range(0, len(Ytt)):
            self.matrice[ypred[i],Ytt[i]] += 1
            self.total += 1
    def __str__(self):
        return str(self.matrice)
    def rappelCalcul(self):
        TP,TN,FP,FN = 0,0,0,0
        

# Prediction
model = knntrain(Xt,Yt)
y_pred = model.predict(Xtt)
mat = ConfMatrix(y_pred, Ytt)
print(mat)
print(mat.rappelCalcul())
print(y_pred)
