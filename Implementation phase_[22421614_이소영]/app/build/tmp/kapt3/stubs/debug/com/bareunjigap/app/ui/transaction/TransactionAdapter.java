package com.bareunjigap.app.ui.transaction;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0018B5\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0016J\u001c\u0010\u000e\u001a\u00020\n2\n\u0010\u000f\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0010\u001a\u00020\rH\u0016J\u001c\u0010\u0011\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\rH\u0016J\"\u0010\u0015\u001a\u00020\n2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/bareunjigap/app/ui/transaction/TransactionAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/bareunjigap/app/ui/transaction/TransactionAdapter$ViewHolder;", "transactions", "", "Lcom/bareunjigap/app/data/entity/Transaction;", "categories", "Lcom/bareunjigap/app/data/entity/Category;", "onItemClick", "Lkotlin/Function1;", "", "(Ljava/util/List;Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateData", "newTransactions", "newCategories", "ViewHolder", "app_debug"})
public final class TransactionAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.bareunjigap.app.ui.transaction.TransactionAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.bareunjigap.app.data.entity.Transaction> transactions;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.bareunjigap.app.data.entity.Category> categories;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.bareunjigap.app.data.entity.Transaction, kotlin.Unit> onItemClick = null;
    
    public TransactionAdapter(@org.jetbrains.annotations.NotNull
    java.util.List<com.bareunjigap.app.data.entity.Transaction> transactions, @org.jetbrains.annotations.NotNull
    java.util.List<com.bareunjigap.app.data.entity.Category> categories, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.bareunjigap.app.data.entity.Transaction, kotlin.Unit> onItemClick) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.bareunjigap.app.ui.transaction.TransactionAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.bareunjigap.app.ui.transaction.TransactionAdapter.ViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    public final void updateData(@org.jetbrains.annotations.NotNull
    java.util.List<com.bareunjigap.app.data.entity.Transaction> newTransactions, @org.jetbrains.annotations.NotNull
    java.util.List<com.bareunjigap.app.data.entity.Category> newCategories) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/bareunjigap/app/ui/transaction/TransactionAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/bareunjigap/app/databinding/ItemTransactionBinding;", "(Lcom/bareunjigap/app/ui/transaction/TransactionAdapter;Lcom/bareunjigap/app/databinding/ItemTransactionBinding;)V", "getBinding", "()Lcom/bareunjigap/app/databinding/ItemTransactionBinding;", "app_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.bareunjigap.app.databinding.ItemTransactionBinding binding = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull
        com.bareunjigap.app.databinding.ItemTransactionBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.bareunjigap.app.databinding.ItemTransactionBinding getBinding() {
            return null;
        }
    }
}