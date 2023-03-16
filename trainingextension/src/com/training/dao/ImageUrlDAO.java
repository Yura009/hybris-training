package com.training.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.product.daos.ProductDao;

public interface ImageUrlDAO extends ProductDao {
    String getPrimaryImageUrlByProductCode(String productCode, CatalogVersionModel catalogVersion);
}
