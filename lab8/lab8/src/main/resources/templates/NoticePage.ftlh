<#import "/spring.ftl" as spring>
<#import "commons.ftlh" as c>

<@c.page>
    <form method="post">
        <div class="text">
            <div class="name">
                <label for="text">Text</label>
            </div>
            <label>
                <textarea name="content" style="resize: none; width:40rem; height:15rem;"></textarea>
            </label>
        </div>
        <@c.error "noticeForm.content"/>
        <div class="button-field">
            <input type="submit" value="Add">
        </div>
    </form>
</@c.page>