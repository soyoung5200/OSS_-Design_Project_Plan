package com.bareunjigap.app.ui.theme;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0013H\u0002J$\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0013H\u0016J\u001a\u0010 \u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\"\u001a\u00020\u0013H\u0002J\u0010\u0010#\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/bareunjigap/app/ui/theme/ThemeFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/bareunjigap/app/databinding/FragmentThemeBinding;", "adapter", "Lcom/bareunjigap/app/ui/theme/ThemeAdapter;", "binding", "getBinding", "()Lcom/bareunjigap/app/databinding/FragmentThemeBinding;", "categoryRepo", "Lcom/bareunjigap/app/data/repository/CategoryRepository;", "session", "Lcom/bareunjigap/app/util/SessionManager;", "themeRepo", "Lcom/bareunjigap/app/data/repository/ThemeGroupRepository;", "txRepo", "Lcom/bareunjigap/app/data/repository/TransactionRepository;", "confirmDeleteTheme", "", "theme", "Lcom/bareunjigap/app/data/entity/ThemeGroup;", "loadThemes", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "showAddThemeDialog", "showThemeTransactionPicker", "app_debug"})
public final class ThemeFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable
    private com.bareunjigap.app.databinding.FragmentThemeBinding _binding;
    private com.bareunjigap.app.util.SessionManager session;
    private com.bareunjigap.app.data.repository.ThemeGroupRepository themeRepo;
    private com.bareunjigap.app.data.repository.TransactionRepository txRepo;
    private com.bareunjigap.app.data.repository.CategoryRepository categoryRepo;
    private com.bareunjigap.app.ui.theme.ThemeAdapter adapter;
    
    public ThemeFragment() {
        super();
    }
    
    private final com.bareunjigap.app.databinding.FragmentThemeBinding getBinding() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void loadThemes() {
    }
    
    private final void showThemeTransactionPicker(com.bareunjigap.app.data.entity.ThemeGroup theme) {
    }
    
    private final void showAddThemeDialog() {
    }
    
    private final void confirmDeleteTheme(com.bareunjigap.app.data.entity.ThemeGroup theme) {
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
}