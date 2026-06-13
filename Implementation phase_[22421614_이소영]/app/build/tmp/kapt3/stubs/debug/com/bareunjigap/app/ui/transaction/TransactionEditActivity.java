package com.bareunjigap.app.ui.transaction;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0002J\b\u0010\u001e\u001a\u00020\u001cH\u0002J\u0012\u0010\u001f\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\b\u0010\"\u001a\u00020\u001cH\u0014J\b\u0010#\u001a\u00020\u001cH\u0002J\b\u0010$\u001a\u00020\u001cH\u0002J\b\u0010%\u001a\u00020\u001cH\u0002J\b\u0010&\u001a\u00020\u001cH\u0002J\u0010\u0010\'\u001a\u00020\u001c2\u0006\u0010(\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/bareunjigap/app/ui/transaction/TransactionEditActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/bareunjigap/app/databinding/ActivityTransactionEditBinding;", "categoryList", "", "Lcom/bareunjigap/app/data/entity/Category;", "categoryRepo", "Lcom/bareunjigap/app/data/repository/CategoryRepository;", "existingTx", "Lcom/bareunjigap/app/data/entity/Transaction;", "isExpense", "", "selectedDateMs", "", "session", "Lcom/bareunjigap/app/util/SessionManager;", "themeList", "", "Lcom/bareunjigap/app/data/entity/ThemeGroup;", "themeRepo", "Lcom/bareunjigap/app/data/repository/ThemeGroupRepository;", "txId", "", "txRepo", "Lcom/bareunjigap/app/data/repository/TransactionRepository;", "confirmDelete", "", "loadCategories", "loadThemes", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "refreshCategorySpinner", "saveTransaction", "setupUI", "showAddCategoryDialog", "updateToggleStyle", "expense", "app_debug"})
public final class TransactionEditActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.bareunjigap.app.databinding.ActivityTransactionEditBinding binding;
    private com.bareunjigap.app.data.repository.TransactionRepository txRepo;
    private com.bareunjigap.app.data.repository.CategoryRepository categoryRepo;
    private com.bareunjigap.app.data.repository.ThemeGroupRepository themeRepo;
    private com.bareunjigap.app.util.SessionManager session;
    private int txId = -1;
    @org.jetbrains.annotations.Nullable
    private com.bareunjigap.app.data.entity.Transaction existingTx;
    private long selectedDateMs;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.bareunjigap.app.data.entity.Category> categoryList;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.bareunjigap.app.data.entity.ThemeGroup> themeList;
    private boolean isExpense = true;
    
    public TransactionEditActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    private final void setupUI() {
    }
    
    private final void updateToggleStyle(boolean expense) {
    }
    
    private final void loadCategories() {
    }
    
    private final void refreshCategorySpinner() {
    }
    
    private final void showAddCategoryDialog() {
    }
    
    private final void loadThemes() {
    }
    
    private final void saveTransaction() {
    }
    
    private final void confirmDelete() {
    }
}