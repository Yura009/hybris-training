package com.training.dao.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.category.model.CategoryModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@IntegrationTest
public class ProductModelDAOImplIntegrationTest {

    @InjectMocks
    private ProductModelDAOImpl productModelDAO;

    @Mock
    private ModelService modelService;

    private CategoryModel categoryModel;

    private CatalogVersionModel catalogVersionModel;
    private String productCode;
    private int offset;
    private int limit;

    @Before
    public void setUp() {
        productCode = "123";
        categoryModel = getCategoryModel();
        offset = 0;
        limit = 10;
        catalogVersionModel = getCatalogVersionModel();
    }

    private CategoryModel getCategoryModel() {
        final String categoryCode = "cat001";
        final CategoryModel categoryModel = this.modelService.create(CategoryModel.class);
        categoryModel.setCode(categoryCode);
        this.modelService.save(categoryModel);
        return categoryModel;
    }

    private CatalogVersionModel getCatalogVersionModel() {
        final String catalogVersion = "Staged";
        final CatalogVersionModel catalogVersionModel = this.modelService.create(CatalogVersionModel.class);
        categoryModel.setCode(catalogVersion);
        this.modelService.save(catalogVersionModel);
        return catalogVersionModel;
    }

    @Test
    public void testGetProductModelByCategoryCodeAndCatalogVersion() {
        // given
        final List<ProductModel> searchResult = productModelDAO.getProductModelByCategoryCodeAndCatalogVersion(
                productCode,
                categoryModel,
                catalogVersionModel,
                offset,
                limit);

        // then
        assertNotNull(searchResult);
        assertEquals(0, searchResult.size());
    }
}
