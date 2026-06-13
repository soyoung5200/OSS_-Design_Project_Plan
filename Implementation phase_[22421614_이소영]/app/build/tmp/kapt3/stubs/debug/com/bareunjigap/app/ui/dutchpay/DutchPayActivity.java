package com.bareunjigap.app.ui.dutchpay;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\u0012\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/bareunjigap/app/ui/dutchpay/DutchPayActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/bareunjigap/app/databinding/ActivityDutchpayBinding;", "calculatedShare", "", "currentTx", "Lcom/bareunjigap/app/data/entity/Transaction;", "originalAmount", "txId", "txRepo", "Lcom/bareunjigap/app/data/repository/TransactionRepository;", "applyDutchPay", "", "calculate", "loadTransaction", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class DutchPayActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.bareunjigap.app.databinding.ActivityDutchpayBinding binding;
    private com.bareunjigap.app.data.repository.TransactionRepository txRepo;
    private int txId = -1;
    private int originalAmount = 0;
    private int calculatedShare = 0;
    @org.jetbrains.annotations.Nullable
    private com.bareunjigap.app.data.entity.Transaction currentTx;
    
    public DutchPayActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadTransaction() {
    }
    
    private final void calculate() {
    }
    
    private final void applyDutchPay() {
    }
}