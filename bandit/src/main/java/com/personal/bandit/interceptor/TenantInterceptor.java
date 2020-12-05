package com.personal.bandit.interceptor;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.springframework.stereotype.Component;
import com.personal.bandit.model.TenantSupport;
import com.personal.bandit.util.TenantContext;
import aj.org.objectweb.asm.Type;

@Component
public class TenantInterceptor extends EmptyInterceptor {

	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof TenantSupport) {
			((TenantSupport) entity).setTenantId(TenantContext.getCurrentTenant());
		}
		return false;
	}

	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if (entity instanceof TenantSupport) {
			((TenantSupport) entity).setTenantId(TenantContext.getCurrentTenant());
		}
	}

	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if (entity instanceof TenantSupport) {
			((TenantSupport) entity).setTenantId(TenantContext.getCurrentTenant());
		}
		return false;
	}
}
