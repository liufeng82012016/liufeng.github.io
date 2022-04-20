# git 
### git reset
1. git reset -- soft commitId，回退已经提交的commit，并将commit修改内容放回到暂存区。如果是提交且push的commit，需要推送到仓库才会生效
2. git reset -- hard commitId

### git stash
1. git stash：将未提交的代码保存起来，切换分支时检查是否更新
2. git stash save ""：将未提交的代码保存起来，切换分支时检查是否更新，并添加注释
3. git stash list ：查询所有的stash列表
4. git stash apply：应用最近的stash
5. git stash pop： 应用最近一次的stash，随后删除该记录
6. git stash drop：删除最近的一次stash
7. git stash apply stash@{1}：应用第二条记录，drop命令和pop命令同样可以跟下标