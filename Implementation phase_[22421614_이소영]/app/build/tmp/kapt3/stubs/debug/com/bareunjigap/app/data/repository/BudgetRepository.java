package com.bareunjigap.app.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J#\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u001b\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/bareunjigap/app/data/repository/BudgetRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "dao", "Lcom/bareunjigap/app/data/dao/BudgetDao;", "getByMonth", "Lcom/bareunjigap/app/data/entity/Budget;", "userId", "", "yearMonth", "", "(ILjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLatest", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "save", "", "budget", "(Lcom/bareunjigap/app/data/entity/Budget;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class BudgetRepository {
    @org.jetbrains.annotations.NotNull
    private final com.bareunjigap.app.data.dao.BudgetDao dao = null;
    
    public BudgetRepository(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object save(@org.jetbrains.annotations.NotNull
    com.bareunjigap.app.data.entity.Budget budget, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getByMonth(int userId, @org.jetbrains.annotations.NotNull
    java.lang.String yearMonth, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.bareunjigap.app.data.entity.Budget> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getLatest(int userId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.bareunjigap.app.data.entity.Budget> $completion) {
        return null;
    }
}