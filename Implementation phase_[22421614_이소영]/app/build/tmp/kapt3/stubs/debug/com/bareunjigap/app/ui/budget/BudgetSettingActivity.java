package com.bareunjigap.app.ui.budget;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u000e\u001a\u00020\nH\u0002J\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/bareunjigap/app/ui/budget/BudgetSettingActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/bareunjigap/app/databinding/ActivityBudgetSettingBinding;", "budgetRepo", "Lcom/bareunjigap/app/data/repository/BudgetRepository;", "session", "Lcom/bareunjigap/app/util/SessionManager;", "loadCurrentBudget", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "saveBudget", "updateAvailable", "income", "", "fixed", "app_debug"})
public final class BudgetSettingActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.bareunjigap.app.databinding.ActivityBudgetSettingBinding binding;
    private com.bareunjigap.app.data.repository.BudgetRepository budgetRepo;
    private com.bareunjigap.app.util.SessionManager session;
    
    public BudgetSettingActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadCurrentBudget() {
    }
    
    private final void saveBudget() {
    }
    
    private final void updateAvailable(int income, int fixed) {
    }
}