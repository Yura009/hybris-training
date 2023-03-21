package com.training.dao;

import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.daos.ProductDao;

import java.util.List;

public interface ProductModelDAO extends ProductDao {
    List<ProductModel> getProductModelByCategoryCodeAndCatalogVersion(String productCode, CategoryModel categoryModel, CatalogVersionModel catalogVersion, int offset, int limit);
}
