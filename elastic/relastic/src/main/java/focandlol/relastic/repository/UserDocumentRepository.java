package focandlol.relastic.repository;

import focandlol.relastic.document.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserDocumentRepository extends ElasticsearchRepository<UserDocument, String> {

}
