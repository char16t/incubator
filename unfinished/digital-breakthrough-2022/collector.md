Проверим, как загружать результат:

```python
df_issues_train = pd.read_csv("/content/drive/MyDrive/champ/collector/train_issues.csv")
df_comment_train = pd.read_csv("/content/drive/MyDrive/champ/collector/train_comments.csv")
df_emp = pd.read_csv("/content/drive/MyDrive/champ/collector/employees.csv")
df_issues_test = pd.read_csv("/content/drive/MyDrive/champ/collector/test_issues.csv")
df_comment_test = pd.read_csv("/content/drive/MyDrive/champ/collector/test_comments.csv")

df_issues_test.head(3)
df_result = pd.DataFrame()
df_result["id"] = df_issues_test["id"]
df_result["overall_worklogs"] = 0
df_result.to_csv('solution.csv', index=False)
```

Пробую Catboost:

```
import math
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from catboost import CatBoostRegressor
from catboost import Pool
from sklearn.metrics import r2_score

df_issues_train = pd.read_csv("/content/drive/MyDrive/champ/collector/train_issues.csv")
df_comment_train = pd.read_csv("/content/drive/MyDrive/champ/collector/train_comments.csv")
df_emp = pd.read_csv("/content/drive/MyDrive/champ/collector/employees.csv")
df_issues_test = pd.read_csv("/content/drive/MyDrive/champ/collector/test_issues.csv")
df_comment_test = pd.read_csv("/content/drive/MyDrive/champ/collector/test_comments.csv")

merged = pd.merge(df_issues_train, df_emp.add_prefix('assignee_'), left_on="assignee_id", right_on="assignee_id", how='inner')
merged = pd.merge(merged, df_emp.add_prefix('creator_'), left_on="creator_id", right_on="creator_id", how='inner')
merged.to_csv("merged.csv", index=False)

y = df_issues_train['overall_worklogs']
X = pd.DataFrame()
X['project_id'] = pd.Categorical(df_issues_train['project_id'])
X['assignee_id'] = merged['assignee_id'].astype('category')
X['creator_id'] = merged['creator_id'].astype('category')
# X["day"] = merged['created'].map(lambda x : int(x.split("-")[2].split()[0]))
# X["month"] =  merged['created'].map(lambda x : int(x.split("-")[1]))

#X['self_assign'] = X.apply(lambda row: True if row['assignee_id'] == row['creator_id'] else False, axis=1)
#X['assignee_position'] = merged['assignee_position'].astype('category')
#X['creator_position'] = merged['creator_position'].astype('category')

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.3, random_state=42)

model = CatBoostRegressor(iterations=2)
pool_train = Pool(X_train, y_train, cat_features = ['project_id', 'assignee_id', 'creator_id'])
pool_test = Pool(X_test, cat_features = ['project_id', 'assignee_id', 'creator_id'])

model.fit(pool_train)
pred = model.predict(pool_test)
score = r2_score(y_test, pred)
print("R2 value is " + str(score))


# Сохранить результат для отправки
X_result = pd.DataFrame()
merged_result = pd.merge(df_issues_test, df_emp.add_prefix('assignee_'), left_on="assignee_id", right_on="assignee_id", how='inner')
merged_result = pd.merge(merged_result, df_emp.add_prefix('creator_'), left_on="creator_id", right_on="creator_id", how='inner')

X_result['project_id'] = pd.Categorical(merged_result["project_id"])
X_result['assignee_id'] = merged_result['assignee_id'].astype('category')
X_result['creator_id'] = merged_result['creator_id'].astype('category')
# X_result["day"] = merged_result['created'].map(lambda x : int(x.split("-")[2].split()[0]))
# X_result["month"] =  merged_result['created'].map(lambda x : int(x.split("-")[1]))
#X_result['self_assign'] = X_result.apply(lambda row: True if row['assignee_id'] == row['creator_id'] else False, axis=1)
#X_result['assignee_position'] = merged_result['assignee_position'].astype('category')
#X_result['creator_position'] = merged_result['creator_position'].astype('category')

pool_result = Pool(X_result, cat_features = ['project_id', 'assignee_id', 'creator_id'])
pred_result = model.predict(pool_result)

df_issues_test.head(3)
df_result = pd.DataFrame()
df_result["id"] = df_issues_test["id"]
df_result["overall_worklogs"] = pred_result.astype(np.int64)
df_result.to_csv('solution.csv', index=False)

#model.plot_tree(
#    tree_idx=0,
#    pool=pool_train
#).render(filename='g1', format='png')
```
