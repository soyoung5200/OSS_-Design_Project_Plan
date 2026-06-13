package com.bareunjigap.app.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\'J\u001f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u001f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\u000f\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0013"}, d2 = {"Lcom/bareunjigap/app/data/dao/FixedScheduleDao;", "", "delete", "", "schedule", "Lcom/bareunjigap/app/data/entity/FixedSchedule;", "(Lcom/bareunjigap/app/data/entity/FixedSchedule;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllByUser", "Landroidx/lifecycle/LiveData;", "", "userId", "", "getAllByUserSync", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByDay", "day", "insert", "", "update", "app_debug"})
@androidx.room.Dao
public abstract interface FixedScheduleDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull
    com.bareunjigap.app.data.entity.FixedSchedule schedule, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull
    com.bareunjigap.app.data.entity.FixedSchedule schedule, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull
    com.bareunjigap.app.data.entity.FixedSchedule schedule, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM fixed_schedules WHERE userId = :userId")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.bareunjigap.app.data.entity.FixedSchedule>> getAllByUser(int userId);
    
    @androidx.room.Query(value = "SELECT * FROM fixed_schedules WHERE userId = :userId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAllByUserSync(int userId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.bareunjigap.app.data.entity.FixedSchedule>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM fixed_schedules WHERE dayOfMonth = :day")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getByDay(int day, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.bareunjigap.app.data.entity.FixedSchedule>> $completion);
}