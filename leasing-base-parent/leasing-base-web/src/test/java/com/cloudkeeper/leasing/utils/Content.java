package com.cloudkeeper.leasing.utils;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.StringUtils;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.List;

import static com.cloudkeeper.leasing.utils.DefinitionUtil.*;
import static com.cloudkeeper.leasing.utils.GenerateTemplateCodeUtil.*;

class Content {

    @Nonnull
    static String getDomainContent() {
        return "package " + PackageSuffix.DOMAIN.getPackage() + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import com.cloudkeeper.leasing.base.domain.BaseEntity;" +
                LINE_SEPARATOR +
                "import io.swagger.annotations.ApiModel;" +
                LINE_SEPARATOR +
                "import io.swagger.annotations.ApiModelProperty;" +
                LINE_SEPARATOR +
                "import lombok.AllArgsConstructor;" +
                LINE_SEPARATOR +
                "import lombok.Getter;" +
                LINE_SEPARATOR +
                "import lombok.NoArgsConstructor;" +
                LINE_SEPARATOR +
                "import lombok.Setter;" +
                LINE_SEPARATOR +
                "import lombok.experimental.Accessors;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import javax.persistence.Column;" +
                LINE_SEPARATOR +
                "import javax.persistence.Entity;" +
                LINE_SEPARATOR +
                "import javax.persistence.Table;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "/**" +
                LINE_SEPARATOR +
                " * " + CLASS_EXPLAIN +
                LINE_SEPARATOR +
                " * @author " + DOC_AUTHOR +
                LINE_SEPARATOR +
                " */" +
                LINE_SEPARATOR +
                "@ApiModel(value = \"" + CLASS_EXPLAIN + "\", description = \"" + CLASS_EXPLAIN + "\")" +
                LINE_SEPARATOR +
                "@Getter" +
                LINE_SEPARATOR +
                "@Setter" +
                LINE_SEPARATOR +
                "@Accessors(chain = true)" +
                LINE_SEPARATOR +
                "@NoArgsConstructor" +
                LINE_SEPARATOR +
                "@AllArgsConstructor" +
                LINE_SEPARATOR +
                "@Entity" +
                LINE_SEPARATOR +
                "@Table(name = \"" + TABLE_NAME + "\")" +
                LINE_SEPARATOR +
                "public class " + PackageSuffix.DOMAIN.getClassName() + " extends BaseEntity {" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "/** 名称 */" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "@ApiModelProperty(value = \"名称\", position = 10, required = true)" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "@Column(length = 60)" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "private String name;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "}";
    }

    @Nonnull
    private static String fieldStr() {
        List<Field> fieldList = getField();
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : fieldList) {
            stringBuilder.append(fieldStr(field));
        }
        return stringBuilder.toString();
    }

    @Nonnull
    private static String fieldStr(@Nonnull Field field) {
        if (!BASIC_CLASS_TYPE_ALL.contains(field.getType().getSimpleName())) {
            return "";
        }
        ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
        StringBuilder stringBuilder = new StringBuilder();
        if (apiModelProperty != null && StringUtils.hasText(apiModelProperty.value())) {
            stringBuilder.append(TAB_SEPARATOR + "/** ").append(apiModelProperty.value()).append(" */");
            stringBuilder.append(LINE_SEPARATOR);
            stringBuilder.append(TAB_SEPARATOR + "@ApiModelProperty(value = \"").append(apiModelProperty.value()).append("\", position = ").append(apiModelProperty.position());
            if (apiModelProperty.required()) {
                stringBuilder.append(", required = true");
            }
            stringBuilder.append(")");
            stringBuilder.append(LINE_SEPARATOR);
        }
        stringBuilder.append(TAB_SEPARATOR + "private ").append(field.getType().getSimpleName()).append(" ").append(field.getName()).append(";");
        stringBuilder.append(LINE_SEPARATOR);
        stringBuilder.append(LINE_SEPARATOR);
        return stringBuilder.toString();
    }

    @Nonnull
    static String getVOContent() {
        return "package " + PackageSuffix.VO.getPackage() + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import com.cloudkeeper.leasing.base.vo.BaseVO;" +
                LINE_SEPARATOR +
                "import io.swagger.annotations.ApiModel;" +
                LINE_SEPARATOR +
                "import io.swagger.annotations.ApiModelProperty;" +
                LINE_SEPARATOR +
                "import lombok.AllArgsConstructor;" +
                LINE_SEPARATOR +
                "import lombok.Getter;" +
                LINE_SEPARATOR +
                "import lombok.NoArgsConstructor;" +
                LINE_SEPARATOR +
                "import lombok.Setter;" +
                LINE_SEPARATOR +
                "import lombok.experimental.Accessors;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import java.math.BigDecimal;" +
                LINE_SEPARATOR +
                "import java.time.LocalDate;" +
                LINE_SEPARATOR +
                "import java.time.LocalDateTime;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "/**" +
                LINE_SEPARATOR +
                " * " + PackageSuffix.VO.getExplain() +
                LINE_SEPARATOR +
                " * @author " + DOC_AUTHOR +
                LINE_SEPARATOR +
                " */" +
                LINE_SEPARATOR +
                "@ApiModel(value = \"" + PackageSuffix.VO.getExplain() + "\", description = \"" + PackageSuffix.VO.getExplain() + "\")" +
                LINE_SEPARATOR +
                "@Getter" +
                LINE_SEPARATOR +
                "@Setter" +
                LINE_SEPARATOR +
                "@Accessors(chain = true)" +
                LINE_SEPARATOR +
                "@NoArgsConstructor" +
                LINE_SEPARATOR +
                "@AllArgsConstructor" +
                LINE_SEPARATOR +
                "public class " + PackageSuffix.VO.getClassName() + " extends BaseVO {" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                fieldStr() +
                "}";
    }

    @Nonnull
    static String getDTOContent() {
        return "package " + PackageSuffix.DTO.getPackage() + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import com.cloudkeeper.leasing.base.dto.BaseEditDTO;" +
                LINE_SEPARATOR +
                "import io.swagger.annotations.ApiModel;" +
                LINE_SEPARATOR +
                "import io.swagger.annotations.ApiModelProperty;" +
                LINE_SEPARATOR +
                "import lombok.AllArgsConstructor;" +
                LINE_SEPARATOR +
                "import lombok.Getter;" +
                LINE_SEPARATOR +
                "import lombok.NoArgsConstructor;" +
                LINE_SEPARATOR +
                "import lombok.Setter;" +
                LINE_SEPARATOR +
                "import lombok.experimental.Accessors;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import java.math.BigDecimal;" +
                LINE_SEPARATOR +
                "import java.time.LocalDate;" +
                LINE_SEPARATOR +
                "import java.time.LocalDateTime;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "/**" +
                LINE_SEPARATOR +
                " * " + PackageSuffix.DTO.getExplain() +
                LINE_SEPARATOR +
                " * @author " + DOC_AUTHOR +
                LINE_SEPARATOR +
                " */" +
                LINE_SEPARATOR +
                "@ApiModel(value = \"" + PackageSuffix.DTO.getExplain() + "\", description = \"" + PackageSuffix.DTO.getExplain() + "\")" +
                LINE_SEPARATOR +
                "@Getter" +
                LINE_SEPARATOR +
                "@Setter" +
                LINE_SEPARATOR +
                "@Accessors(chain = true)" +
                LINE_SEPARATOR +
                "@NoArgsConstructor" +
                LINE_SEPARATOR +
                "@AllArgsConstructor" +
                LINE_SEPARATOR +
                "public class " + PackageSuffix.DTO.getClassName() + " extends BaseEditDTO {" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                fieldStr() +
                "}";
    }

    @Nonnull
    static String getSearchableContent() {
        return "package " + PackageSuffix.SEARCHABLE.getPackage() + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import com.cloudkeeper.leasing.base.dto.BaseSearchable;" +
                LINE_SEPARATOR +
                "import io.swagger.annotations.ApiModel;" +
                LINE_SEPARATOR +
                "import io.swagger.annotations.ApiModelProperty;" +
                LINE_SEPARATOR +
                "import lombok.AllArgsConstructor;" +
                LINE_SEPARATOR +
                "import lombok.Getter;" +
                LINE_SEPARATOR +
                "import lombok.NoArgsConstructor;" +
                LINE_SEPARATOR +
                "import lombok.Setter;" +
                LINE_SEPARATOR +
                "import lombok.experimental.Accessors;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import java.math.BigDecimal;" +
                LINE_SEPARATOR +
                "import java.time.LocalDate;" +
                LINE_SEPARATOR +
                "import java.time.LocalDateTime;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "/**" +
                LINE_SEPARATOR +
                " * " + PackageSuffix.SEARCHABLE.getExplain() +
                LINE_SEPARATOR +
                " * @author " + DOC_AUTHOR +
                LINE_SEPARATOR +
                " */" +
                LINE_SEPARATOR +
                "@ApiModel(value = \"" + PackageSuffix.SEARCHABLE.getExplain() + "\", description = \"" + PackageSuffix.SEARCHABLE.getExplain() + "\")" +
                LINE_SEPARATOR +
                "@Getter" +
                LINE_SEPARATOR +
                "@Setter" +
                LINE_SEPARATOR +
                "@Accessors(chain = true)" +
                LINE_SEPARATOR +
                "@NoArgsConstructor" +
                LINE_SEPARATOR +
                "@AllArgsConstructor" +
                LINE_SEPARATOR +
                "public class " + PackageSuffix.SEARCHABLE.getClassName() + " extends BaseSearchable {" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                fieldStr() +
                "}";
    }

    @Nonnull
    static String getRepositoryContent() {
        return "package " + PackageSuffix.REPOSITORY.getPackage() + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import " + PackageSuffix.DOMAIN.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import com.cloudkeeper.leasing.base.repository.BaseRepository;" +
                LINE_SEPARATOR +
                "import org.springframework.stereotype.Repository;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "/**" +
                LINE_SEPARATOR +
                " * " + PackageSuffix.REPOSITORY.getExplain() +
                LINE_SEPARATOR +
                " * @author " + DOC_AUTHOR +
                LINE_SEPARATOR +
                " */" +
                LINE_SEPARATOR +
                "@Repository" +
                LINE_SEPARATOR +
                "public interface " + PackageSuffix.REPOSITORY.getClassName() + " extends BaseRepository<" + PackageSuffix.DOMAIN.getClassName() + "> {" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "}";
    }

    @Nonnull
    static String getRepositoryTestContent() {
        return "package " + PackageSuffix.REPOSITORY_TEST.getPackage() + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import " + PackageSuffix.DOMAIN.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import org.junit.After;" +
                LINE_SEPARATOR +
                "import org.junit.Before;" +
                LINE_SEPARATOR +
                "import org.junit.Test;" +
                LINE_SEPARATOR +
                "import org.junit.runner.RunWith;" +
                LINE_SEPARATOR +
                "import org.springframework.beans.factory.annotation.Autowired;" +
                LINE_SEPARATOR +
                "import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;" +
                LINE_SEPARATOR +
                "import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;" +
                LINE_SEPARATOR +
                "import org.springframework.test.context.junit4.SpringRunner;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import static org.assertj.core.api.Assertions.assertThat;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "/**" +
                LINE_SEPARATOR +
                " * " + PackageSuffix.REPOSITORY.getExplain() + " 测试" +
                LINE_SEPARATOR +
                " * @author " + DOC_AUTHOR +
                LINE_SEPARATOR +
                " */" +
                LINE_SEPARATOR +
                "@RunWith(SpringRunner.class)" +
                LINE_SEPARATOR +
                "@DataJpaTest" +
                LINE_SEPARATOR +
                "public class " + PackageSuffix.REPOSITORY_TEST.getClassName() + " {" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "/** 测试 entityManager */" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "@Autowired" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "private TestEntityManager entityManager;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "/** " + PackageSuffix.REPOSITORY.getExplain() + " */" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "@Autowired" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "private " + PackageSuffix.REPOSITORY.getClassName() + " " + lowerCase(PackageSuffix.REPOSITORY.getClassName()) + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                TAB_SEPARATOR + "@Before" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "public void setUp() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + CLASS_NAME + " " + lowerCase(CLASS_NAME) + " = new " + CLASS_NAME + "();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + lowerCase(CLASS_NAME) + " = entityManager.persist(" + lowerCase(CLASS_NAME) + ");" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                TAB_SEPARATOR + "@After" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "public void tearDown() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + lowerCase(PackageSuffix.REPOSITORY.getClassName()) + ".deleteAll();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                TAB_SEPARATOR + "@Test" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "public void save() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + CLASS_NAME + " " + lowerCase(CLASS_NAME) + " = " + lowerCase(PackageSuffix.REPOSITORY.getClassName()) + ".save(new " + CLASS_NAME + "());" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(" + lowerCase(CLASS_NAME) + ").isNotNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "}";
    }

    @Nonnull
    static String getServiceContent() {
        return "package " + PackageSuffix.SERVICE.getPackage() + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import " + PackageSuffix.DOMAIN.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import com.cloudkeeper.leasing.base.service.BaseService;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "/**" +
                LINE_SEPARATOR +
                " * " + PackageSuffix.SERVICE.getExplain() +
                LINE_SEPARATOR +
                " * @author " + DOC_AUTHOR +
                LINE_SEPARATOR +
                " */" +
                LINE_SEPARATOR +
                "public interface " + PackageSuffix.SERVICE.getClassName() + " extends BaseService<" + PackageSuffix.DOMAIN.getClassName() + "> {" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "}";
    }

    @Nonnull
    static String getServiceImplContent() {
        return "package " + PackageSuffix.SERVICE_IMPL.getPackage() + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import com.cloudkeeper.leasing.base.repository.BaseRepository;" +
                LINE_SEPARATOR +
                "import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.DOMAIN.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.REPOSITORY.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.SERVICE.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import lombok.RequiredArgsConstructor;" +
                LINE_SEPARATOR +
                "import org.springframework.beans.factory.annotation.Autowired;" +
                LINE_SEPARATOR +
                "import org.springframework.data.domain.ExampleMatcher;" +
                LINE_SEPARATOR +
                "import org.springframework.stereotype.Service;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "/**" +
                LINE_SEPARATOR +
                " * " + PackageSuffix.SERVICE.getExplain() +
                LINE_SEPARATOR +
                " * @author " + DOC_AUTHOR +
                LINE_SEPARATOR +
                " */" +
                LINE_SEPARATOR +
                "@Service" +
                LINE_SEPARATOR +
                "@RequiredArgsConstructor(onConstructor = @__(@Autowired))" +
                LINE_SEPARATOR +
                "public class " + PackageSuffix.SERVICE_IMPL.getClassName() + " extends BaseServiceImpl<" + PackageSuffix.DOMAIN.getClassName() + "> implements " + PackageSuffix.SERVICE.getClassName() + " {" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "/** " + PackageSuffix.REPOSITORY.getExplain() + " */" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "private final " + PackageSuffix.REPOSITORY.getClassName() + " " + lowerCase(PackageSuffix.REPOSITORY.getClassName()) + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "@Override" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "protected BaseRepository<" + PackageSuffix.DOMAIN.getClassName() + "> getBaseRepository() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "return " + lowerCase(PackageSuffix.REPOSITORY.getClassName()) + ";" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "@Override" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "public ExampleMatcher defaultExampleMatcher() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "return super.defaultExampleMatcher()" + strFieldNameList() +
                ";" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "}";
    }

    @Nonnull
    static String getServiceTestContent() {
        return "package " + PackageSuffix.SERVICE_TEST.getPackage() + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import " + PackageSuffix.DOMAIN.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import org.junit.After;" +
                LINE_SEPARATOR +
                "import org.junit.Before;" +
                LINE_SEPARATOR +
                "import org.junit.Test;" +
                LINE_SEPARATOR +
                "import org.junit.runner.RunWith;" +
                LINE_SEPARATOR +
                "import org.springframework.beans.factory.annotation.Autowired;" +
                LINE_SEPARATOR +
                "import org.springframework.boot.test.context.SpringBootTest;" +
                LINE_SEPARATOR +
                "import org.springframework.test.context.junit4.SpringRunner;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import static org.assertj.core.api.Assertions.assertThat;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "/**" +
                LINE_SEPARATOR +
                " * " + PackageSuffix.SERVICE.getExplain() + " 测试" +
                LINE_SEPARATOR +
                " * @author " + DOC_AUTHOR +
                LINE_SEPARATOR +
                " */" +
                LINE_SEPARATOR +
                "@RunWith(SpringRunner.class)" +
                LINE_SEPARATOR +
                "@SpringBootTest" +
                LINE_SEPARATOR +
                "public class " + PackageSuffix.SERVICE_TEST.getClassName() + " {" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "/** " + PackageSuffix.SERVICE.getExplain() + " */" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "@Autowired" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "private " + PackageSuffix.SERVICE.getClassName() + " " + lowerCase(PackageSuffix.SERVICE.getClassName()) + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                TAB_SEPARATOR + "@Before" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "public void setUp() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + CLASS_NAME + " " + lowerCase(CLASS_NAME) + " = new " + CLASS_NAME + "();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + lowerCase(CLASS_NAME) + " = " + lowerCase(PackageSuffix.SERVICE.getClassName()) + ".save(" + lowerCase(CLASS_NAME) + ");" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                TAB_SEPARATOR + "@After" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "public void tearDown() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + lowerCase(PackageSuffix.SERVICE.getClassName()) + ".deleteAll();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                TAB_SEPARATOR + "@Test" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "public void save() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + CLASS_NAME + " " + lowerCase(CLASS_NAME) + " = " + lowerCase(PackageSuffix.SERVICE.getClassName()) + ".save(new " + CLASS_NAME + "());" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(" + lowerCase(CLASS_NAME) + ").isNotNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "}";
    }

    @Nonnull
    private static String strFieldNameList() {
        List<Field> fieldList = getField();
        StringBuilder stringBuilder = new StringBuilder();
        for (Field field : fieldList) {
            if (BASIC_CLASS_TYPE_STRING.contains(field.getType().getSimpleName()) && !field.getName().contains("Id") && !field.getName().equals("createdBy") && !field.getName().equals("modifiedBy")) {
                stringBuilder.append(LINE_SEPARATOR);
                stringBuilder.append(TAB_SEPARATOR).append(TAB_SEPARATOR).append(TAB_SEPARATOR).append(TAB_SEPARATOR);
                stringBuilder.append(".withMatcher(\"").append(field.getName()).append("\", ExampleMatcher.GenericPropertyMatchers.contains())");
            }
        }
        return stringBuilder.toString();
    }

    @Nonnull
    static String getControllerContent() {
        return "package " + PackageSuffix.CONTROLLER.getPackage() + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import com.cloudkeeper.leasing.base.controller.BaseController;" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.DTO.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.SEARCHABLE.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.VO.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import io.swagger.annotations.Api;" +
                LINE_SEPARATOR +
                "import org.springframework.web.bind.annotation.RequestMapping;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "/**" +
                LINE_SEPARATOR +
                " * " + PackageSuffix.CONTROLLER.getExplain() +
                LINE_SEPARATOR +
                " * @author " + DOC_AUTHOR +
                LINE_SEPARATOR +
                " */" +
                LINE_SEPARATOR +
                "@Api(value = \"" + CLASS_EXPLAIN + "\", tags = \"" + CLASS_EXPLAIN + "\")" +
                LINE_SEPARATOR +
                "@RequestMapping(\"/" + lowerCase(CLASS_NAME) + "\")" +
                LINE_SEPARATOR +
                "public interface " + PackageSuffix.CONTROLLER.getClassName() + " extends BaseController<" + PackageSuffix.DTO.getClassName() + ", " + PackageSuffix.SEARCHABLE.getClassName() + ", " + PackageSuffix.VO.getClassName() + "> {" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                "}";
    }

    @Nonnull
    static String getControllerImplContent() {
        return "package " + PackageSuffix.CONTROLLER_IMPL.getPackage() + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import com.cloudkeeper.leasing.base.controller.impl.BaseControllerImpl;" +
                LINE_SEPARATOR +
                "import com.cloudkeeper.leasing.base.service.BaseService;" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.CONTROLLER.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.DOMAIN.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.DTO.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.SEARCHABLE.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.SERVICE.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.VO.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import lombok.RequiredArgsConstructor;" +
                LINE_SEPARATOR +
                "import org.springframework.beans.factory.annotation.Autowired;" +
                LINE_SEPARATOR +
                "import org.springframework.web.bind.annotation.RestController;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "/**" +
                LINE_SEPARATOR +
                " * " + PackageSuffix.CONTROLLER.getExplain() +
                LINE_SEPARATOR +
                " * @author " + DOC_AUTHOR +
                LINE_SEPARATOR +
                " */" +
                LINE_SEPARATOR +
                "@RestController" +
                LINE_SEPARATOR +
                "@RequiredArgsConstructor(onConstructor = @__(@Autowired))" +
                LINE_SEPARATOR +
                "public class " + PackageSuffix.CONTROLLER_IMPL.getClassName() + " extends BaseControllerImpl<" + PackageSuffix.DOMAIN.getClassName() + ", " + PackageSuffix.DTO.getClassName() + ", " + PackageSuffix.SEARCHABLE.getClassName() + ", " + PackageSuffix.VO.getClassName() + "> implements " + PackageSuffix.CONTROLLER.getClassName() + " {" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "/** " + PackageSuffix.SERVICE.getExplain() + " */" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "private final " + PackageSuffix.SERVICE.getClassName() + " " + lowerCase(PackageSuffix.SERVICE.getClassName()) + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "@Override" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "protected BaseService<" + PackageSuffix.DOMAIN.getClassName() + "> getBaseService() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "return " + lowerCase(PackageSuffix.SERVICE.getClassName()) + ";" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                "}";
    }

    @Nonnull
    static String getControllerTestContent() {
        return "package " + PackageSuffix.CONTROLLER_TEST.getPackage() + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import com.cloudkeeper.leasing.base.dto.BaseSearchable;" +
                LINE_SEPARATOR +
                "import com.cloudkeeper.leasing.base.model.ResponseMessageConstants;" +
                LINE_SEPARATOR +
                "import com.cloudkeeper.leasing.base.model.Result;" +
                LINE_SEPARATOR +
                "import com.cloudkeeper.leasing.base.utils.RestPageImpl;" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.DOMAIN.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.DTO.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.SEARCHABLE.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.SERVICE.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import " + PackageSuffix.VO.getFullClassName() + ";" +
                LINE_SEPARATOR +
                "import org.junit.After;" +
                LINE_SEPARATOR +
                "import org.junit.Before;" +
                LINE_SEPARATOR +
                "import org.junit.Test;" +
                LINE_SEPARATOR +
                "import org.junit.runner.RunWith;" +
                LINE_SEPARATOR +
                "import org.springframework.beans.factory.annotation.Autowired;" +
                LINE_SEPARATOR +
                "import org.springframework.boot.test.context.SpringBootTest;" +
                LINE_SEPARATOR +
                "import org.springframework.boot.test.mock.mockito.MockBean;" +
                LINE_SEPARATOR +
                "import org.springframework.boot.test.web.client.TestRestTemplate;" +
                LINE_SEPARATOR +
                "import org.springframework.core.ParameterizedTypeReference;" +
                LINE_SEPARATOR +
                "import org.springframework.data.domain.PageImpl;" +
                LINE_SEPARATOR +
                "import org.springframework.data.domain.Pageable;" +
                LINE_SEPARATOR +
                "import org.springframework.data.domain.Sort;" +
                LINE_SEPARATOR +
                "import org.springframework.http.HttpEntity;" +
                LINE_SEPARATOR +
                "import org.springframework.http.HttpMethod;" +
                LINE_SEPARATOR +
                "import org.springframework.http.ResponseEntity;" +
                LINE_SEPARATOR +
                "import org.springframework.test.context.junit4.SpringRunner;" +
                LINE_SEPARATOR +
                "import org.springframework.util.StringUtils;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import java.util.Collections;" +
                LINE_SEPARATOR +
                "import java.util.List;" +
                LINE_SEPARATOR +
                "import java.util.Optional;" +
                LINE_SEPARATOR +
                "import java.util.UUID;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "import static org.assertj.core.api.Assertions.assertThat;" +
                LINE_SEPARATOR +
                "import static org.mockito.ArgumentMatchers.any;" +
                LINE_SEPARATOR +
                "import static org.mockito.BDDMockito.given;" +
                LINE_SEPARATOR +
                "import static org.mockito.Mockito.doAnswer;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                "/**" +
                LINE_SEPARATOR +
                " * " + PackageSuffix.CONTROLLER.getExplain() + " 测试" +
                LINE_SEPARATOR +
                " * @author " + DOC_AUTHOR +
                LINE_SEPARATOR +
                " */" +
                LINE_SEPARATOR +
                "@RunWith(SpringRunner.class)" +
                LINE_SEPARATOR +
                "@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)" +
                LINE_SEPARATOR +
                "public class " + PackageSuffix.CONTROLLER_TEST.getClassName() + " {" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "/** rest 请求工具 */" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "@Autowired" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "private TestRestTemplate restTemplate;" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "/** " + PackageSuffix.SERVICE.getExplain() + " */" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "@MockBean" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "private " + PackageSuffix.SERVICE.getClassName() + " " + lowerCase(PackageSuffix.SERVICE.getClassName()) + ";" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "private String id = UUID.randomUUID().toString();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "private " + PackageSuffix.DTO.getClassName() + " " + lowerCase(PackageSuffix.DTO.getClassName()) + " = new " + PackageSuffix.DTO.getClassName() + "();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "private " + PackageSuffix.DOMAIN.getClassName() + " " + lowerCase(PackageSuffix.DOMAIN.getClassName()) + " = (" + PackageSuffix.DOMAIN.getClassName() + ") " + lowerCase(PackageSuffix.DTO.getClassName()) + ".convert(" + PackageSuffix.DOMAIN.getClassName() + ".class).setId(id);" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "private ParameterizedTypeReference<Result> type = new ParameterizedTypeReference<Result>() { };" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "private ParameterizedTypeReference<Result<" + PackageSuffix.VO.getClassName() + ">> typeVO = new ParameterizedTypeReference<Result<" + PackageSuffix.VO.getClassName() + ">>() { };" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "private ParameterizedTypeReference<Result<List<" + PackageSuffix.VO.getClassName() + ">>> typeList = new ParameterizedTypeReference<Result<List<" + PackageSuffix.VO.getClassName() + ">>>() { };" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "private ParameterizedTypeReference<Result<RestPageImpl<" + PackageSuffix.VO.getClassName() + ">>> typePage = new ParameterizedTypeReference<Result<RestPageImpl<" + PackageSuffix.VO.getClassName() + ">>>() { };" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                TAB_SEPARATOR + "@Before" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "public void setUp() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("given(%s.findOptionalById(id)).willReturn(Optional.of(%s));", lowerCase(PackageSuffix.SERVICE.getClassName()), lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "doAnswer(invocationOnMock -> {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR + String.format("%s %s = invocationOnMock.getArgument(0);", CLASS_NAME, lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR + String.format("if (!StringUtils.hasText(%s.getId())) {", lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR + String.format("%s.setId(UUID.randomUUID().toString());", lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR + String.format("return %s;", lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("}).when(%s).save(any(%s.class));", lowerCase(PackageSuffix.SERVICE.getClassName()), CLASS_NAME) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("List<%s> %sList = Collections.singletonList(%s);", CLASS_NAME, lowerCase(CLASS_NAME), lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("given(%s.findAll(any(BaseSearchable.class), any(Sort.class))).willReturn(%sList);", lowerCase(PackageSuffix.SERVICE.getClassName()), lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("given(%s.findAll(any(BaseSearchable.class), any(Pageable.class))).willAnswer(invocationOnMock -> new PageImpl<>(%sList, invocationOnMock.getArgument(1), %sList.size()));", lowerCase(PackageSuffix.SERVICE.getClassName()), lowerCase(CLASS_NAME), lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                TAB_SEPARATOR + "@After" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "public void tearDown() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                TAB_SEPARATOR + "@Test" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "public void findOne() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("ResponseEntity<Result<%s>> responseEntity = restTemplate.exchange(\"/%s/{id}\", HttpMethod.GET, null, typeVO, id);", PackageSuffix.VO.getClassName(), lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("Result<%s> result = responseEntity.getBody();", PackageSuffix.VO.getClassName()) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result).isNotNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getContent()).isNotNull();" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("responseEntity = restTemplate.exchange(\"/%s/{id}\", HttpMethod.GET, null, typeVO, id + \"1\");", lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "result = responseEntity.getBody();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result).isNotNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getContent()).isNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                TAB_SEPARATOR + "@Test" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "public void add() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("HttpEntity<%s> requestEntity = new HttpEntity<>(%s);", PackageSuffix.DTO.getClassName(), lowerCase(PackageSuffix.DTO.getClassName())) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("ResponseEntity<Result<%s>> responseEntity = restTemplate.exchange(\"/%s/\", HttpMethod.POST, requestEntity, typeVO);", PackageSuffix.VO.getClassName(), lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("Result<%s> result = responseEntity.getBody();", PackageSuffix.VO.getClassName()) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result).isNotNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getContent()).isNotNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getContent().getId()).isNotNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                TAB_SEPARATOR + "@Test" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "public void update() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("HttpEntity<%s> requestEntity = new HttpEntity<>(%s);", PackageSuffix.DTO.getClassName(), lowerCase(PackageSuffix.DTO.getClassName())) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("ResponseEntity<Result<%s>> responseEntity = restTemplate.exchange(\"/%s/{id}\", HttpMethod.PUT, requestEntity, typeVO, UUID.randomUUID().toString());", PackageSuffix.VO.getClassName(), lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("Result<%s> result = responseEntity.getBody();", PackageSuffix.VO.getClassName()) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result).isNotNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getCode()).isEqualTo(Result.ResultCode.FAIL.getCode());" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getContent()).isNull();" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("responseEntity = restTemplate.exchange(\"/%s/{id}\", HttpMethod.PUT, requestEntity, typeVO, id);", lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "result = responseEntity.getBody();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result).isNotNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getContent()).isNotNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                TAB_SEPARATOR + "@Test" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "public void delete() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("ResponseEntity<Result> responseEntity = restTemplate.exchange(\"/%s/{id}\", HttpMethod.DELETE, null, type, UUID.randomUUID().toString());", lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "Result result = responseEntity.getBody();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result).isNotNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getMsg()).isEqualTo(ResponseMessageConstants.DELETE_SUCCESS);" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                TAB_SEPARATOR + "@Test" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "public void list() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("HttpEntity<%s> requestEntity = new HttpEntity<>(new %s());", PackageSuffix.SEARCHABLE.getClassName(), PackageSuffix.SEARCHABLE.getClassName()) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("ResponseEntity<Result<List<%s>>> responseEntity = restTemplate.exchange(\"/%s/list\", HttpMethod.POST, requestEntity, typeList);", PackageSuffix.VO.getClassName(), lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("Result<List<%s>> result = responseEntity.getBody();", PackageSuffix.VO.getClassName()) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result).isNotNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getContent()).isNotNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getContent()).isNotEmpty();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                TAB_SEPARATOR + "@Test" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "public void page() {" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("HttpEntity<%s> requestEntity = new HttpEntity<>(new %s());", PackageSuffix.SEARCHABLE.getClassName(), PackageSuffix.SEARCHABLE.getClassName()) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("ResponseEntity<Result<RestPageImpl<%s>>> responseEntity = restTemplate.exchange(\"/%s/page\", HttpMethod.POST, requestEntity, typePage);", PackageSuffix.VO.getClassName(), lowerCase(CLASS_NAME)) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + String.format("Result<RestPageImpl<%s>> result = responseEntity.getBody();", PackageSuffix.VO.getClassName()) +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result).isNotNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getCode()).isEqualTo(Result.ResultCode.OK.getCode());" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getContent()).isNotNull();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + TAB_SEPARATOR + "assertThat(result.getContent().getContent()).isNotEmpty();" +
                LINE_SEPARATOR +
                TAB_SEPARATOR + "}" +
                LINE_SEPARATOR +
                LINE_SEPARATOR +

                "}";
    }

    @Nonnull
    static String getLiquibaseXML() {
        List<Field> fields = getField(true);
        StringBuilder xml = new StringBuilder();
        xml.append("-----------------------liquibase start----------------------");
        xml.append(LINE_SEPARATOR);
        xml.append(TAB_SEPARATOR + "<changeSet id=\"" + CHANGE_SET_ID + "\" author=\"").append(DOC_AUTHOR).append("\">");
        xml.append(LINE_SEPARATOR);
        xml.append(TAB_SEPARATOR + TAB_SEPARATOR + "<comment>" + CLASS_EXPLAIN + "表创建</comment>");
        xml.append(LINE_SEPARATOR);
        xml.append(TAB_SEPARATOR + TAB_SEPARATOR + "<createTable tableName=\"").append(getTableName()).append("\" remarks=\"").append(CLASS_EXPLAIN).append("\">");
        xml.append(LINE_SEPARATOR);
        xml.append(TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR + "<column name=\"id\" type=\"varchar(36)\" remarks=\"主键id\">");
        xml.append(LINE_SEPARATOR);
        xml.append(TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR + "<constraints primaryKey=\"true\" nullable=\"false\" primaryKeyName=\"pk_").append(getTableName()).append("\"/>");
        xml.append(LINE_SEPARATOR);
        xml.append(TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR + "</column>");
        xml.append(LINE_SEPARATOR);
        for (Field field : fields) {
            String fieldXml = getLiquibaseXML(field);
            if (!StringUtils.hasText(fieldXml)) {
                continue;
            }
            xml.append(fieldXml);
        }
        xml.append(TAB_SEPARATOR + TAB_SEPARATOR + "</createTable>");
        xml.append(LINE_SEPARATOR);
        xml.append(TAB_SEPARATOR + TAB_SEPARATOR + "<rollback>");
        xml.append(LINE_SEPARATOR);
        xml.append(TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR + "<dropTable tableName=\"").append(getTableName()).append("\"/>");
        xml.append(LINE_SEPARATOR);
        xml.append(TAB_SEPARATOR + TAB_SEPARATOR + "</rollback>");
        xml.append(LINE_SEPARATOR);
        xml.append(TAB_SEPARATOR + "</changeSet>");
        xml.append(LINE_SEPARATOR);
        xml.append("-----------------------liquibase end----------------------");
        return xml.toString();
    }

    @Nonnull
    private static String getLiquibaseXML(@Nonnull Field field) {
        String fieldName = field.getName();
        String fieldType = "";
        String doc = "";
        Column column = field.getAnnotation(Column.class);
        if (column != null && StringUtils.hasText(column.name())) {
            fieldName = column.name();
        }
        if (BASIC_CLASS_TYPE_STRING.contains(field.getType().getSimpleName())) {
            if (column != null) {
                fieldType = "varchar(" + column.length() + ")";
            } else {
                fieldType = "varchar(255)";
            }
        } else if (BASIC_CLASS_TYPE_AMOUNT.contains(field.getType().getSimpleName())) {
            if (column != null) {
                if (StringUtils.hasText(column.columnDefinition())) {
                    fieldType = column.columnDefinition();
                }
            } else {
                fieldType = "double(16,4)";
            }
        } else if (BASIC_CLASS_TYPE_NUMBER.contains(field.getType().getSimpleName())) {
            if (column != null) {
                if (StringUtils.hasText(column.columnDefinition())) {
                    fieldType = column.columnDefinition();
                }
            } else {
                fieldType = "int";
            }
        } else if (BASIC_CLASS_TYPE_LOCAL_DATE.contains(field.getType().getSimpleName())) {
            fieldType = "date";
        } else if (BASIC_CLASS_TYPE_LOCAL_DATE_TIME.contains(field.getType().getSimpleName())) {
            fieldType = "datetime";
        } else {
            return "";
        }
        ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
        if (apiModelProperty != null) {
            doc = apiModelProperty.value();
        }
//        if (fieldName.equals("createdBy") || fieldName.equals("modifiedBy")) {
//            String suffix = fieldName.equals("createdBy") ? "_cby" : "_mby";
//            return TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR +
//                    "<column name=\"" + fieldName + "\" type=\"" + fieldType + "\" remarks=\"" + doc + "\">" +
//                    LINE_SEPARATOR +
//                    TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR +
//                    "<constraints referencedTableName=\"ck_id_principal\" referencedColumnNames=\"id\" foreignKeyName=\"fk_" + TABLE_NAME.toLowerCase() + suffix + "\"/>" +
//                    LINE_SEPARATOR +
//                    TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR +
//                    "</column>" +
//                    LINE_SEPARATOR;
//        }
        return TAB_SEPARATOR + TAB_SEPARATOR + TAB_SEPARATOR + String.format("<column name=\"%s\" type=\"%s\" remarks=\"%s\"/>", fieldName, fieldType, doc) + LINE_SEPARATOR;
    }
}
