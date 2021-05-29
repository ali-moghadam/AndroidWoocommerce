package com.alirnp.androidwoocommerceapp.model.user


import com.google.gson.annotations.SerializedName

data class Allcaps(
    @SerializedName("switch_themes")
    val switchThemes: Boolean,
    @SerializedName("edit_themes")
    val editThemes: Boolean,
    @SerializedName("activate_plugins")
    val activatePlugins: Boolean,
    @SerializedName("edit_plugins")
    val editPlugins: Boolean,
    @SerializedName("edit_users")
    val editUsers: Boolean,
    @SerializedName("edit_files")
    val editFiles: Boolean,
    @SerializedName("manage_options")
    val manageOptions: Boolean,
    @SerializedName("moderate_comments")
    val moderateComments: Boolean,
    @SerializedName("manage_categories")
    val manageCategories: Boolean,
    @SerializedName("manage_links")
    val manageLinks: Boolean,
    @SerializedName("upload_files")
    val uploadFiles: Boolean,
    @SerializedName("import")
    val `import`: Boolean,
    @SerializedName("unfiltered_html")
    val unfilteredHtml: Boolean,
    @SerializedName("edit_posts")
    val editPosts: Boolean,
    @SerializedName("edit_others_posts")
    val editOthersPosts: Boolean,
    @SerializedName("edit_published_posts")
    val editPublishedPosts: Boolean,
    @SerializedName("publish_posts")
    val publishPosts: Boolean,
    @SerializedName("edit_pages")
    val editPages: Boolean,
    @SerializedName("read")
    val read: Boolean,
    @SerializedName("level_10")
    val level10: Boolean,
    @SerializedName("level_9")
    val level9: Boolean,
    @SerializedName("level_8")
    val level8: Boolean,
    @SerializedName("level_7")
    val level7: Boolean,
    @SerializedName("level_6")
    val level6: Boolean,
    @SerializedName("level_5")
    val level5: Boolean,
    @SerializedName("level_4")
    val level4: Boolean,
    @SerializedName("level_3")
    val level3: Boolean,
    @SerializedName("level_2")
    val level2: Boolean,
    @SerializedName("level_1")
    val level1: Boolean,
    @SerializedName("level_0")
    val level0: Boolean,
    @SerializedName("edit_others_pages")
    val editOthersPages: Boolean,
    @SerializedName("edit_published_pages")
    val editPublishedPages: Boolean,
    @SerializedName("publish_pages")
    val publishPages: Boolean,
    @SerializedName("delete_pages")
    val deletePages: Boolean,
    @SerializedName("delete_others_pages")
    val deleteOthersPages: Boolean,
    @SerializedName("delete_published_pages")
    val deletePublishedPages: Boolean,
    @SerializedName("delete_posts")
    val deletePosts: Boolean,
    @SerializedName("delete_others_posts")
    val deleteOthersPosts: Boolean,
    @SerializedName("delete_published_posts")
    val deletePublishedPosts: Boolean,
    @SerializedName("delete_private_posts")
    val deletePrivatePosts: Boolean,
    @SerializedName("edit_private_posts")
    val editPrivatePosts: Boolean,
    @SerializedName("read_private_posts")
    val readPrivatePosts: Boolean,
    @SerializedName("delete_private_pages")
    val deletePrivatePages: Boolean,
    @SerializedName("edit_private_pages")
    val editPrivatePages: Boolean,
    @SerializedName("read_private_pages")
    val readPrivatePages: Boolean,
    @SerializedName("delete_users")
    val deleteUsers: Boolean,
    @SerializedName("create_users")
    val createUsers: Boolean,
    @SerializedName("unfiltered_upload")
    val unfilteredUpload: Boolean,
    @SerializedName("edit_dashboard")
    val editDashboard: Boolean,
    @SerializedName("update_plugins")
    val updatePlugins: Boolean,
    @SerializedName("delete_plugins")
    val deletePlugins: Boolean,
    @SerializedName("install_plugins")
    val installPlugins: Boolean,
    @SerializedName("update_themes")
    val updateThemes: Boolean,
    @SerializedName("install_themes")
    val installThemes: Boolean,
    @SerializedName("update_core")
    val updateCore: Boolean,
    @SerializedName("list_users")
    val listUsers: Boolean,
    @SerializedName("remove_users")
    val removeUsers: Boolean,
    @SerializedName("promote_users")
    val promoteUsers: Boolean,
    @SerializedName("edit_theme_options")
    val editThemeOptions: Boolean,
    @SerializedName("delete_themes")
    val deleteThemes: Boolean,
    @SerializedName("export")
    val export: Boolean,
    @SerializedName("manage_woocommerce")
    val manageWoocommerce: Boolean,
    @SerializedName("view_woocommerce_reports")
    val viewWoocommerceReports: Boolean,
    @SerializedName("edit_product")
    val editProduct: Boolean,
    @SerializedName("read_product")
    val readProduct: Boolean,
    @SerializedName("delete_product")
    val deleteProduct: Boolean,
    @SerializedName("edit_products")
    val editProducts: Boolean,
    @SerializedName("edit_others_products")
    val editOthersProducts: Boolean,
    @SerializedName("publish_products")
    val publishProducts: Boolean,
    @SerializedName("read_private_products")
    val readPrivateProducts: Boolean,
    @SerializedName("delete_products")
    val deleteProducts: Boolean,
    @SerializedName("delete_private_products")
    val deletePrivateProducts: Boolean,
    @SerializedName("delete_published_products")
    val deletePublishedProducts: Boolean,
    @SerializedName("delete_others_products")
    val deleteOthersProducts: Boolean,
    @SerializedName("edit_private_products")
    val editPrivateProducts: Boolean,
    @SerializedName("edit_published_products")
    val editPublishedProducts: Boolean,
    @SerializedName("manage_product_terms")
    val manageProductTerms: Boolean,
    @SerializedName("edit_product_terms")
    val editProductTerms: Boolean,
    @SerializedName("delete_product_terms")
    val deleteProductTerms: Boolean,
    @SerializedName("assign_product_terms")
    val assignProductTerms: Boolean,
    @SerializedName("edit_shop_order")
    val editShopOrder: Boolean,
    @SerializedName("read_shop_order")
    val readShopOrder: Boolean,
    @SerializedName("delete_shop_order")
    val deleteShopOrder: Boolean,
    @SerializedName("edit_shop_orders")
    val editShopOrders: Boolean,
    @SerializedName("edit_others_shop_orders")
    val editOthersShopOrders: Boolean,
    @SerializedName("publish_shop_orders")
    val publishShopOrders: Boolean,
    @SerializedName("read_private_shop_orders")
    val readPrivateShopOrders: Boolean,
    @SerializedName("delete_shop_orders")
    val deleteShopOrders: Boolean,
    @SerializedName("delete_private_shop_orders")
    val deletePrivateShopOrders: Boolean,
    @SerializedName("delete_published_shop_orders")
    val deletePublishedShopOrders: Boolean,
    @SerializedName("delete_others_shop_orders")
    val deleteOthersShopOrders: Boolean,
    @SerializedName("edit_private_shop_orders")
    val editPrivateShopOrders: Boolean,
    @SerializedName("edit_published_shop_orders")
    val editPublishedShopOrders: Boolean,
    @SerializedName("manage_shop_order_terms")
    val manageShopOrderTerms: Boolean,
    @SerializedName("edit_shop_order_terms")
    val editShopOrderTerms: Boolean,
    @SerializedName("delete_shop_order_terms")
    val deleteShopOrderTerms: Boolean,
    @SerializedName("assign_shop_order_terms")
    val assignShopOrderTerms: Boolean,
    @SerializedName("edit_shop_coupon")
    val editShopCoupon: Boolean,
    @SerializedName("read_shop_coupon")
    val readShopCoupon: Boolean,
    @SerializedName("delete_shop_coupon")
    val deleteShopCoupon: Boolean,
    @SerializedName("edit_shop_coupons")
    val editShopCoupons: Boolean,
    @SerializedName("edit_others_shop_coupons")
    val editOthersShopCoupons: Boolean,
    @SerializedName("publish_shop_coupons")
    val publishShopCoupons: Boolean,
    @SerializedName("read_private_shop_coupons")
    val readPrivateShopCoupons: Boolean,
    @SerializedName("delete_shop_coupons")
    val deleteShopCoupons: Boolean,
    @SerializedName("delete_private_shop_coupons")
    val deletePrivateShopCoupons: Boolean,
    @SerializedName("delete_published_shop_coupons")
    val deletePublishedShopCoupons: Boolean,
    @SerializedName("delete_others_shop_coupons")
    val deleteOthersShopCoupons: Boolean,
    @SerializedName("edit_private_shop_coupons")
    val editPrivateShopCoupons: Boolean,
    @SerializedName("edit_published_shop_coupons")
    val editPublishedShopCoupons: Boolean,
    @SerializedName("manage_shop_coupon_terms")
    val manageShopCouponTerms: Boolean,
    @SerializedName("edit_shop_coupon_terms")
    val editShopCouponTerms: Boolean,
    @SerializedName("delete_shop_coupon_terms")
    val deleteShopCouponTerms: Boolean,
    @SerializedName("assign_shop_coupon_terms")
    val assignShopCouponTerms: Boolean,
    @SerializedName("administrator")
    val administrator: Boolean
)