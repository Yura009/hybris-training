package com.training.dao.impl;

import com.training.dao.ImageUrlDAO;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.daos.impl.DefaultProductDao;
import de.hybris.platform.search.restriction.SearchRestrictionService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

public class ImageUrlDAOImpl extends DefaultProductDao implements ImageUrlDAO {

    private SearchRestrictionService searchRestrictionService;

    public ImageUrlDAOImpl(String typecode, SearchRestrictionService searchRestrictionService) {
        super(typecode);
        this.searchRestrictionService = searchRestrictionService;
    }

    private static final String FIND_PRIMARY_IMAGE_URL = "SELECT {" + ProductModel.PICTURE +
            "} FROM {" + ProductModel._TYPECODE + "}, {" + CatalogVersionModel._TYPECODE +
            "} WHERE {" + ProductModel.CODE + "} = ?code AND {" + CatalogVersionModel.VERSION + "} = ?version";

    @Override
    public String getPrimaryImageUrlByProductCode(String productCode, CatalogVersionModel catalogVersion) {
        getSearchRestrictionService().disableSearchRestrictions();
        FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(FIND_PRIMARY_IMAGE_URL);
        searchQuery.addQueryParameter("code", productCode);
        searchQuery.addQueryParameter("version", catalogVersion);

        SearchResult<String> searchResult = getFlexibleSearchService().search(searchQuery);
        if (searchResult.getResult().get(0).isEmpty()) {
            return null;
        }
        return searchResult.getResult().get(0);
    }

    public SearchRestrictionService getSearchRestrictionService() {
        return searchRestrictionService;
    }
}
