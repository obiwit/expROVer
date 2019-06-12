package ua.deti.exprover.models;

// side menu
public class NavItem {
    String mTitle;
    String mSubtitle;
    int mIcon;

    public NavItem(String title, String subtitle, int icon) {
        mTitle = title;
        mSubtitle = subtitle;
        mIcon = icon;
    }

    public String getTitle() {
        return mTitle;
    }
    public String getSubtitle() {
        return mSubtitle;
    }
    public int getIcon() {
        return mIcon;
    }
}
