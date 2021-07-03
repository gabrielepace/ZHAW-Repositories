class DbSpamFilter():
    def filter(self, record):
        return not (record.module == 'schema' or record.funcName == 'debug_sql' or record.module == 'autoreload')
