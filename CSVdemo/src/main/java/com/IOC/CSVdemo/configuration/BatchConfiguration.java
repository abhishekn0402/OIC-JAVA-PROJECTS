package com.IOC.CSVdemo.configuration;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.IOC.CSVdemo.listener.JobCompletionListener;
import com.IOC.CSVdemo.model.Marksheet;
import com.IOC.CSVdemo.model.Student;
import com.IOC.CSVdemo.processor.StudentItemProcessor;
import com.zaxxer.hikari.HikariDataSource;
@Configuration
@EnableBatchProcessing
//@EnableScheduling
public class BatchConfiguration extends DefaultBatchConfigurer 
{
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public LineMapper<Student> lineMapper() {
		DefaultLineMapper<Student> lineMapper = new DefaultLineMapper<Student>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer() {
			{
				setNames(new String[] { "rollNum", "stdName", "subjectAMark", "subjectBMark" });
			}
		});
		lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<Student>() {
			{
				setTargetType(Student.class);
			}
		});
		return lineMapper;
	}

	@Bean
	public FlatFileItemReader<Student> reader() {
	    return new FlatFileItemReaderBuilder<Student>()
		  .name("studentItemReader")		
		  .resource(new ClassPathResource("mytest.csv"))
		  .lineMapper(lineMapper())
		  .linesToSkip(1)
		  .build();
	}

	@Bean
	public JdbcBatchItemWriter<Marksheet> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Marksheet>()
		   .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Marksheet>())
		   .sql("INSERT INTO marksheet (rollNum, studentName, totalMarks) VALUES (:rollNum, :stdName,:totalMarks)")
		   .dataSource(dataSource)
		   .build();
	}

	@Bean
	public ItemProcessor<Student, Marksheet> processor() {
		return new StudentItemProcessor();
	}

	@Bean
	public Job createMarkSheetJob(JobCompletionListener listener, Step step1) {
		return jobBuilderFactory
		  .get("createMarkSheetJob")
		  .incrementer(new RunIdIncrementer())
		  .listener(listener)
		  .flow(step1)
		  .end()
		  .build();
	}

	@Bean
	public Step step1(ItemReader<Student> reader, ItemWriter<Marksheet> writer,
			ItemProcessor<Student, Marksheet> processor) {
		 return stepBuilderFactory
		   .get("step1")
		   .<Student, Marksheet>chunk(5)
		   .reader(reader)
		   .processor(processor)
		   .writer(writer)
		   .build();
	}

	@Bean
	public DataSource getDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/cpdb");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Override
	public void setDataSource(DataSource dataSource)
	{
		
	}
}
