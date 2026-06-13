package com.bareunjigap.app.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J#\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u001b\u0010\t\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0011"}, d2 = {"Lcom/bareunjigap/app/data/dao/BudgetDao;", "", "getByMonth", "Lcom/bareunjigap/app/data/entity/Budget;", "userId", "", "yearMonth", "", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLatest", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insert", "", "budget", "(Lcom/bareunjigap/app/data/entity/Budget;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "", "app_debug"})
@androidx.room.Dao
public abstract interface BudgetDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull
    com.bareunjigap.app.data.entity.Budget budget, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull
    com.bareunjigap.app.data.entity.Budget budget, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM budgets WHERE userId = :userId AND yearMonth = :yearMonth LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getByMonth(int userId, @org.jetbrains.annotations.NotNull
    java.lang.String yearMonth, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.bareunjigap.app.data.entity.Budget> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM budgets WHERE userId = :userId ORDER BY yearMonth DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getLatest(int userId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.bareunjigap.app.data.entity.Budget> $completion);
}