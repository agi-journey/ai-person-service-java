-- V001: Create person and individual_person tables
-- Story: ST-002 - Criar endpoint para cadastrar pessoa física

CREATE TABLE person (
    id UUID PRIMARY KEY,
    type VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT true,
    country_code VARCHAR(10) NOT NULL,
    tax_id VARCHAR(100) NOT NULL,
    tenant_id UUID NOT NULL,
    created_by UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID,
    updated_at TIMESTAMP,
    CONSTRAINT uq_person_tax_id_country UNIQUE (tax_id, country_code)
);

CREATE INDEX idx_person_tax_id_country ON person(tax_id, country_code);
CREATE INDEX idx_person_tenant_id ON person(tenant_id);

CREATE TABLE individual_person (
    person_id UUID PRIMARY KEY,
    state_id VARCHAR(100),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    gender VARCHAR(30),
    marital_status VARCHAR(30),
    tenant_id UUID NOT NULL,
    created_by UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID,
    updated_at TIMESTAMP,
    CONSTRAINT fk_individual_person_person FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);

CREATE INDEX idx_individual_person_tenant ON individual_person(tenant_id);