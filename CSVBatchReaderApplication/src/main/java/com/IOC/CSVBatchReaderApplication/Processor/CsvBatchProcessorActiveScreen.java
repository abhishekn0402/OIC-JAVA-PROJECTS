package com.IOC.CSVBatchReaderApplication.Processor;

import org.springframework.batch.item.ItemProcessor;

import com.IOC.CSVBatchReaderApplication.Model.CSVBatchReaderActiveScreen;

public class CsvBatchProcessorActiveScreen  implements ItemProcessor<CSVBatchReaderActiveScreen,CSVBatchReaderActiveScreen>
{
	@Override
	public CSVBatchReaderActiveScreen process(final CSVBatchReaderActiveScreen cSVBatchReaderActiveScreen) throws Exception
	{
		return cSVBatchReaderActiveScreen;
	}
}
