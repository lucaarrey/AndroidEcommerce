package sophia.com.ecommerce2.adapter;

/**
 * Created by archimede on 06/06/17.
 */

public interface OnAdapterItemClickListener {
    void OnItemClick(int position);
    void OnAddToCartClick(int position);
    void OnShareClick(int position);
}
