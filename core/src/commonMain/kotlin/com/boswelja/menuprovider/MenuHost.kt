package com.boswelja.menuprovider

/**
 * A state holder object designed to store information about the contents of a menu, and allows
 * adding or removing menu items.
 */
public interface MenuHost {

    /**
     * Items that should be displayed in the menu.
     */
    public val menuItems: List<MenuItem>

    /**
     * Add items to [menuItems], triggering recomposition of menus that reference it.
     */
    public fun addItems(vararg newItems: MenuItem)

    /**
     * Remove items from [menuItems], triggering recomposition of menus that reference it.
     */
    public fun removeItems(vararg items: MenuItem)
}
