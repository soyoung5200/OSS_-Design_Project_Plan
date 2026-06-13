package com.bareunjigap.app.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&\u00a8\u0006\u0012"}, d2 = {"Lcom/bareunjigap/app/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "budgetDao", "Lcom/bareunjigap/app/data/dao/BudgetDao;", "categoryDao", "Lcom/bareunjigap/app/data/dao/CategoryDao;", "fixedScheduleDao", "Lcom/bareunjigap/app/data/dao/FixedScheduleDao;", "notificationDao", "Lcom/bareunjigap/app/data/dao/NotificationDao;", "themeGroupDao", "Lcom/bareunjigap/app/data/dao/ThemeGroupDao;", "transactionDao", "Lcom/bareunjigap/app/data/dao/TransactionDao;", "userDao", "Lcom/bareunjigap/app/data/dao/UserDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.bareunjigap.app.data.entity.User.class, com.bareunjigap.app.data.entity.Transaction.class, com.bareunjigap.app.data.entity.Category.class, com.bareunjigap.app.data.entity.ThemeGroup.class, com.bareunjigap.app.data.entity.Budget.class, com.bareunjigap.app.data.entity.FixedSchedule.class, com.bareunjigap.app.data.entity.NotificationEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile
    @org.jetbrains.annotations.Nullable
    private static volatile com.bareunjigap.app.data.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull
    public static final com.bareunjigap.app.data.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.bareunjigap.app.data.dao.UserDao userDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.bareunjigap.app.data.dao.TransactionDao transactionDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.bareunjigap.app.data.dao.CategoryDao categoryDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.bareunjigap.app.data.dao.ThemeGroupDao themeGroupDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.bareunjigap.app.data.dao.BudgetDao budgetDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.bareunjigap.app.data.dao.FixedScheduleDao fixedScheduleDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.bareunjigap.app.data.dao.NotificationDao notificationDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/bareunjigap/app/data/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/bareunjigap/app/data/AppDatabase;", "defaultCategories", "", "Lcom/bareunjigap/app/data/entity/Category;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.bareunjigap.app.data.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
        
        private final java.util.List<com.bareunjigap.app.data.entity.Category> defaultCategories() {
            return null;
        }
    }
}