/*
 *    Copyright 2010 The myBatis Team
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.spring.support;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.util.Assert;

/**
 * Convenient super class for MyBatis SqlSession data access objects. 
 * It gives you access to the template which can then be used to execute SQL methods.
 * <p>
 * This class needs a SqlSessionTemplate or a SqlSessionFactory. 
 * If both are set the SqlSessionFactory will be ignored.
 *
 * @see #setSqlSessionFactory
 * @see #setSqlSessionTemplate
 * @see SqlSessionTemplate
 * @see SqlSessionTemplate#setExceptionTranslator
 * @version $Id$
 */
public abstract class SqlSessionDaoSupport extends DaoSupport {

    private SqlSessionTemplate sqlSessionTemplate;

    private boolean externalTemplate;

    @Autowired(required = false)
    public final void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        if (!this.externalTemplate) {
            this.sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        }
    }
    
    public final SqlSessionFactory getSqlSessionFactory() {
        return this.sqlSessionTemplate.getSqlSessionFactory();
    }

    @Autowired(required = false)
    public final void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.externalTemplate = true;
    }

    public final SqlSessionTemplate getSqlSessionTemplate() {
        return this.sqlSessionTemplate;
    }

    /**
     * {@inheritDoc}
     */
    protected void checkDaoConfig() {
        Assert.notNull(this.sqlSessionTemplate, "Property 'sqlSessionTemplate' is required");
        this.sqlSessionTemplate.afterPropertiesSet();
    }

}