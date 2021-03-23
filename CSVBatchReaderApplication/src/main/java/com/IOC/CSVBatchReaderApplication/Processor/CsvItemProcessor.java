package com.IOC.CSVBatchReaderApplication.Processor;

import org.springframework.batch.item.ItemProcessor;

import com.IOC.CSVBatchReaderApplication.Model.CSVBatchReaderReserveScreen;
public class CsvItemProcessor implements ItemProcessor<CSVBatchReaderReserveScreen,CSVBatchReaderReserveScreen>
{
	@Override
	public CSVBatchReaderReserveScreen process(final CSVBatchReaderReserveScreen csvBatchReaderDTO) throws Exception
	{		
		return csvBatchReaderDTO;
	}	
}
