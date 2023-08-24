# %load metrics.py 

from sklearn.metrics import PrecisionRecallDisplay
import matplotlib.pyplot as plt
from sklearn.metrics import (confusion_matrix, 
                             ConfusionMatrixDisplay, 
                             recall_score, 
                             accuracy_score, 
                             precision_score, 
                             f1_score,
                             average_precision_score,
                             PrecisionRecallDisplay,
                             precision_recall_curve,
                             roc_auc_score,
                             RocCurveDisplay,
                             roc_curve,
                             mean_squared_error,
                             mean_absolute_error,
                             r2_score
                             )

def print_metrics_regression(y,pred,title=None):
    """
    회귀 평가지표를 출력하는 함수
    mse,rmse,mae,r2
    """
    if title is not None:
        print(f"========{title}========")
    print("MSE:",mean_squared_error(y,pred))
    print("RMSE:", mean_squared_error(y,pred,squared=False))
    print("MAE:",mean_absolute_error(y,pred))
    print("R2:",r2_score(y,pred))

    
def plot_roc_curve(y, pos_proba, estimator_name, title=None):
    """
    ROC  Curve 시각화 함수
    [parameter]
        y: ndarray - 정답
        pos_proba : ndarray - 모델이 추정한 양성(positive)일 확률
        estimator_name : str - 범례에 나올 모델의 이름
        title : str - subplot의 제목
    """
    auc_score = roc_auc_score(y, pos_proba)
    fpr, tpr, _ = roc_curve(y, pos_proba)
    RocCurveDisplay(fpr=fpr, tpr=tpr, roc_auc=auc_score, estimator_name=estimator_name).plot()
    if title:
        plt.title(title)
    plt.show()
    
    
def plot_precision_recall_curve(y, pos_proba, estimator_name, title=None):
    """
    Precision Recall Curve 시각화 함수
    [parameter]
        y: ndarray - 정답
        pos_proba : ndarray - 모델이 추정한 양성(positive)일 확률
        estimator_name : str - 범례에 나올 모델의 이름
        title : str - subplot의 제목
    """
    ap_score = average_precision_score(y, pos_proba)
    precision, recall, _ = precision_recall_curve(y, pos_proba)
    PrecisionRecallDisplay(precision, recall, average_precision=ap_score, estimator_name=estimator_name).plot()
    if title:
        plt.title(title)
    plt.show()
    
# confusion matrix  시각화
def plot_confusion_matrix(y, pred, title=None):
    """
    confusion matrix 시각화
    [parameter]
        y: ndarray - 정답
        pred : ndarray - 모델 추정한 label
        title : str -  그래프의 제목
    """
    cm = confusion_matrix(y, pred)
    disp = ConfusionMatrixDisplay(cm)
    disp.plot(cmap="Blues")
    if title:
        plt.title(title)
    plt.show()
    
def print_metrics_classification(y, pred, pos_proba=None, title=None):
    """
    분류 결과에 대한 평가지표를 출력하는 함수
    출력내용 : accuracy, recall, precision, f1 score
    [parameter]
        y: ndarray - 정답
        pred : ndarray - 모델 추정한 label
        pos_proba : ndarray - 모델이 추정한 양성(positive)의 확률, default : None - ap.acore, auc score는 출력안한다
        title : str -  그래프의 제목
    """
    if title:
        print(f"=========={title}==========")
    print("정확도:", accuracy_score(y, pred))
    print("재현율:", recall_score(y, pred))
    print("정밀도:", precision_score(y, pred))
    print("F1점수:", f1_score(y, pred))
    if pos_proba is not None:
        print("Average Precision:", average_precision_score(y, pos_proba))
        print("ROC-AUC", roc_auc_score(y, pos_proba))
