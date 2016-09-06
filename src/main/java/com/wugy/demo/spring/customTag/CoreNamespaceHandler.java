package com.wugy.demo.spring.customTag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class CoreNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		registerBeanDefinitionParser("fileList", new FileListDefinitionParser());
		registerBeanDefinitionParser("fileFilter", new FileFilterDefinitionParser());
	}

}
