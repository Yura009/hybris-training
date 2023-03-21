package com.training.dao.impl;

import com.training.dao.ProductModelDAO;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.daos.impl.DefaultProductDao;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.List;

public class ProductModelDAOImpl extends DefaultProductDao implements ProductModelDAO {

    public ProductModelDAOImpl(String typecode) {
        super(typecode);
    }

    private static final String FIND_PRODUCT_MODEL = "SELECT {" + ProductModel.PK + "} FROM {" +
            ProductModel._TYPECODE + "}, {" + CatalogVersionModel._TYPECODE + "}, {" + CategoryModel._TYPECODE +
            "} WHERE {" + ProductModel.CODE + "} = ?code AND {" + CategoryModel.CODE +
            "} = ?code AND {" + CatalogVersionModel.VERSION + "} = ?version";


    @Override
    public List<ProductModel> getProductModelByCategoryCodeAndCatalogVersion(String productCode, CategoryModel categoryModel, CatalogVersionModel catalogVersion, int offset, int limit) {
        FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(FIND_PRODUCT_MODEL);
        searchQuery.addQueryParameter("code", productCode);
        searchQuery.addQueryParameter("category", categoryModel);
        searchQuery.addQueryParameter("version", catalogVersion);
        searchQuery.setStart(offset);
        searchQuery.setCount(limit);
        SearchResult<ProductModel> searchResult = getFlexibleSearchService().search(searchQuery);
        return searchResult.getResult();
    }
}